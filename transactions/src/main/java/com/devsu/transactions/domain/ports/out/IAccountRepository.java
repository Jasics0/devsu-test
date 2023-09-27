package com.devsu.transactions.domain.ports.out;

import com.devsu.transactions.domain.model.Account;

import java.util.List;

public interface IAccountRepository {
    Account save(Account account);

    Account findById(Long idAccount);

    List<Account> findByIdPerson(String idPerson);

    Account update(Account account, Long idAccount);

    Account updateBalance(Account account);

    void delete(Long idAccount);
}
