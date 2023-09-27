package com.devsu.transactions.infrastructure.web.dto.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionSimpleDTO {
    private Long id;
    private LocalDate date;
    private Long idAccount;
    private Double amount;
}
