package com.devsu.transactions.application.services;

import com.devsu.transactions.domain.model.Account;
import com.devsu.transactions.domain.ports.in.IAccountService;
import com.devsu.transactions.domain.ports.out.IAccountRepository;
import com.devsu.transactions.global.exceptions.DevsuException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;

    @Override
    public Account save(Account account) {

        if (account.getIdPerson() == null || account.getIdPerson().isBlank()) throw new DevsuException(DevsuException.DevsuError.ACCOUNT_DATA_REQUIRED, "The person id is required");


        Account accountToSave = new Account();

        if (account.getAccountType() != null) accountToSave.setAccountType(account.getAccountType());
        if (account.getBalance() != null) accountToSave.setBalance(account.getBalance());
        if (account.getIdPerson() != null) accountToSave.setIdPerson(account.getIdPerson());

        return accountRepository.save(accountToSave);
    }

    @Override
    public Account findByIdAccount(Long idAccount) {

        if (idAccount == null) throw new DevsuException(DevsuException.DevsuError.ACCOUNT_DATA_REQUIRED, "The account id is required");

        return accountRepository.findById(idAccount);
    }

    @Override
    public List<Account> findByClientId(String idPerson) {

        if (idPerson == null || idPerson.isBlank()) throw new DevsuException(DevsuException.DevsuError.ACCOUNT_DATA_REQUIRED, "The person id is required");

        return accountRepository.findByIdPerson(idPerson);
    }

    @Override
    public Account update(Account account, Long idAccount) {
        if (idAccount == null) throw new DevsuException(DevsuException.DevsuError.ACCOUNT_DATA_REQUIRED, "The account id is required");
        if (account == null) throw new DevsuException(DevsuException.DevsuError.ACCOUNT_DATA_REQUIRED, "The account data is required");
        return accountRepository.update(account, idAccount);
    }

    @Override
    public void delete(Long idAccount) {
        if (idAccount == null) throw new DevsuException(DevsuException.DevsuError.ACCOUNT_DATA_REQUIRED, "The account id is required");
        accountRepository.delete(idAccount);
    }
}
