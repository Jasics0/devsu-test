package com.devsu.transactions.application.services;

import com.devsu.transactions.domain.model.Account;
import com.devsu.transactions.domain.model.Report;
import com.devsu.transactions.domain.model.Transaction;
import com.devsu.transactions.domain.ports.in.IAccountService;
import com.devsu.transactions.domain.ports.in.IReportService;
import com.devsu.transactions.domain.ports.in.ITransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final ITransactionService transactionService;

    private final IAccountService accountService;
    private static final String DATE_RANGE_PATTERN =
            "^(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}-(0[1-9]|1[0-9]|2[0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";

    @Override
    public Report getReport(Long idAccount, String range) {

        Account account = accountService.findByIdAccount(idAccount);

        LocalDate[] dates = getDate(range);

        List<Transaction> transactions = transactionService.findByClientIdAndRange(account.getIdPerson(), dates[0], dates[1]);

        return new Report(account, transactions);
    }

    @Override
    public List<Report> getReport(String idPerson, String range) {

        List<Report> reports = new ArrayList<>();

        List<Account> accounts = accountService.findByClientId(idPerson);

        LocalDate[] dates = getDate(range);

        accounts.stream().forEach(account -> {
            List<Transaction> transactions = transactionService.findByAccountAndRange(account.getId(), dates[0], dates[1]);
            if (!transactions.isEmpty()) reports.add(new Report(account, transactions));
        });

        return reports;
    }

    private boolean validateRange(String range) {
        Pattern pattern = Pattern.compile(DATE_RANGE_PATTERN);
        Matcher matcher = pattern.matcher(range);
        return matcher.matches();
    }

    private LocalDate[] getDate(String range) {

        if (!validateRange(range)) throw new IllegalArgumentException("Range must be in format dd/MM/yyyy-dd/MM/yyyy");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String[] dates = range.split("-");

        LocalDate[] localDates = new LocalDate[2];
        localDates[0] = LocalDate.parse(dates[0], formatter);
        localDates[1] = LocalDate.parse(dates[1], formatter);

        if (localDates[0].isAfter(localDates[1])) throw new IllegalArgumentException("The first date must be before the second date");

        return localDates;
    }
}
