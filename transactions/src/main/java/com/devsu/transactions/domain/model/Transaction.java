package com.devsu.transactions.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class Transaction {

    private Long id;
    private LocalDate date;
    private Long idAccount;
    private Double amount;
    private Double balance;

    public Transaction() {
        this.date = LocalDate.now();
    }
}
