package com.devsu.transactions.infrastructure.web.dto.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Builder
@Data
public class ReportDTO {
    private AccountDTO account;
    private List<TransactionSimpleDTO> transactions;
}
