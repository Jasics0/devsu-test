package com.devsu.transactions.infrastructure.mappers;

import com.devsu.transactions.domain.model.Account;
import com.devsu.transactions.domain.model.enums.AccountType;
import com.devsu.transactions.infrastructure.entities.AccountEntity;
import com.devsu.transactions.infrastructure.entities.enums.AccountTypeEntity;

import java.util.List;
import java.util.stream.Collectors;

public class InfraAccountMapper {
    private InfraAccountMapper() {
    }

    public static AccountEntity toEntity(Account account) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setId(account.getId());
        accountEntity.setAccountTypeEntity(AccountTypeEntity.valueOf(account.getAccountType().name()));
        accountEntity.setBalance(account.getBalance());
        accountEntity.setIdPerson(account.getIdPerson());
        accountEntity.setStatus(account.isStatus());
        return accountEntity;
    }

    public static Account toDomain(AccountEntity accountEntity) {
        Account account = new Account();
        account.setId(accountEntity.getId());
        account.setAccountType(AccountType.valueOf(accountEntity.getAccountTypeEntity().name()));
        account.setBalance(accountEntity.getBalance());
        account.setIdPerson(accountEntity.getIdPerson());
        account.setStatus(accountEntity.isStatus());
        return account;
    }

    public static List<Account> toDomainList(List<AccountEntity> accountEntityList) {
        return accountEntityList.stream().map(InfraAccountMapper::toDomain).collect(Collectors.toList());
    }
}
