package com.devsu.transactions.domain.ports.in;

import com.devsu.transactions.domain.model.Account;

import java.util.List;

public interface IAccountService {

    Account save(Account account);

    Account findByIdAccount(Long idAccount);

    List<Account> findByClientId(String idPerson);

    Account update(Account account, Long idAccount);

    void delete(Long idAccount);

}
