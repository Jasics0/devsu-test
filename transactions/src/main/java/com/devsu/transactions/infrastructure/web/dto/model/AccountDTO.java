package com.devsu.transactions.infrastructure.web.dto.model;

import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    private String accountType;
    private Double balance;
    private boolean status;
    private String idPerson;
}
