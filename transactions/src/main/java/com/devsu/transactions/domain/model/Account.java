package com.devsu.transactions.domain.model;

import com.devsu.transactions.domain.model.enums.AccountType;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class Account {

    private Long id;
    private AccountType accountType;
    private Double balance;
    private boolean status;
    private String idPerson;

    public Account() {
        this.id = (long) (Math.random() * 1000000);
        this.accountType = AccountType.AHORROS;
        this.balance = 0.0;
        this.status = true;
    }
}
