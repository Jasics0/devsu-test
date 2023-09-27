package com.devsu.clients;

import lombok.Data;

@Data
public class AccountDTO {
    private Long id;
    private String accountType;
    private Double balance;
    private boolean status;
    private String idPerson;
}
