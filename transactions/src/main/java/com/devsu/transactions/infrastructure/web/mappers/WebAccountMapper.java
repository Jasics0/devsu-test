package com.devsu.transactions.infrastructure.web.mappers;

import com.devsu.transactions.domain.model.Account;
import com.devsu.transactions.domain.model.enums.AccountType;
import com.devsu.transactions.infrastructure.web.dto.model.AccountDTO;

import java.util.List;

public class WebAccountMapper {
    private WebAccountMapper() {
    }

    public static Account toDomain(AccountDTO accountDTO) {
        com.devsu.transactions.domain.model.Account account = new com.devsu.transactions.domain.model.Account();
        account.setId(accountDTO.getId());

        if (accountDTO.getAccountType()!=null){
            account.setAccountType(AccountType.valueOf(accountDTO.getAccountType().toUpperCase()));
        }

        account.setBalance(accountDTO.getBalance());
        account.setStatus(accountDTO.isStatus());
        account.setIdPerson(accountDTO.getIdPerson());
        return account;
    }

    public static AccountDTO toDTO(Account account) {
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setId(account.getId());
        accountDTO.setAccountType(account.getAccountType().toString());
        accountDTO.setBalance(account.getBalance());
        accountDTO.setStatus(account.isStatus());
        accountDTO.setIdPerson(account.getIdPerson());
        return accountDTO;
    }

    public  static List<AccountDTO> toDTOList(List<Account> accountList) {
        return accountList.stream().map(WebAccountMapper::toDTO).toList();
    }
}
