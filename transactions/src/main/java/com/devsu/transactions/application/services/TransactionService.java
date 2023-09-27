package com.devsu.transactions.application.services;

import com.devsu.transactions.domain.model.Account;
import com.devsu.transactions.domain.model.Transaction;
import com.devsu.transactions.domain.ports.in.ITransactionService;
import com.devsu.transactions.domain.ports.out.IAccountRepository;
import com.devsu.transactions.domain.ports.out.ITransactionRepository;
import com.devsu.transactions.global.exceptions.DevsuException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService implements ITransactionService {

    private final ITransactionRepository transactionRepository;
    private final IAccountRepository accountRepository;

    @Override
    public Transaction save(Transaction transaction) {

        if (transaction.getIdAccount() == null)
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The account id is required");
        if (transaction.getAmount() == null || transaction.getAmount() == 0)
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The amount is required");
        if (transaction.getBalance() != null)
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The balance is not required");

        transaction.setDate(LocalDate.now());

        Account account = accountRepository.findById(transaction.getIdAccount());

        if (account == null) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_FOUND);
        }

        double newBalance = account.getBalance() + transaction.getAmount();

        if (newBalance < 0) {
            throw new DevsuException(DevsuException.DevsuError.ACCOUNT_NOT_ENOUGH_BALANCE);
        }

        account.setBalance(newBalance);

        accountRepository.updateBalance(account);

        transaction = transactionRepository.save(transaction);
        transaction.setBalance(newBalance);

        return transaction;
    }

    @Override
    public Transaction findByIdTransaction(Long idTransaction) {

        if (idTransaction == null) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The transaction id is required");
        }
        return transactionRepository.findById(idTransaction);
    }

    @Override
    public List<Transaction> findByAccount(Long idAccount) {

        if (idAccount == null) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The account id is required");
        }
        return transactionRepository.findByIdAccount(idAccount);
    }

    @Override
    public List<Transaction> findByAccountAndRange(Long idAccount, LocalDate startDate, LocalDate endDate) {

        if (idAccount == null) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The account id is required");
        }
        return transactionRepository.findByIdAccountAndRange(idAccount, startDate, endDate);
    }

    @Override
    public List<Transaction> findByClientId(String idPerson) {

        if (idPerson == null || idPerson.isBlank()) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The person id is required");
        }
        return transactionRepository.findByIdPerson(idPerson);
    }

    @Override
    public List<Transaction> findByClientIdAndRange(String idPerson, LocalDate startDate, LocalDate endDate) {

        if (idPerson == null || idPerson.isBlank()) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The person id is required");
        }
        return transactionRepository.findByIdPersonAndRange(idPerson, startDate, endDate);
    }

    @Override
    public Transaction update(Transaction transaction, Long idTransaction) {
        if (idTransaction == null) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The transaction id is required");
        }

        if (transaction == null) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The transaction is required");
        }
        return transactionRepository.update(transaction, idTransaction);
    }

    @Override
    public void delete(Long idTransaction) {
        if (idTransaction == null) {
            throw new DevsuException(DevsuException.DevsuError.TRANSACTION_DATA_REQUIRED, "The transaction id is required");
        }
        transactionRepository.delete(idTransaction);
    }
}
