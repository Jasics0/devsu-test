package com.devsu.transactions.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
public class Report {
    private Account account;
    private List<Transaction> transactions;
}
