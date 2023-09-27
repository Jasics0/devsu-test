package com.devsu.transactions.infrastructure.web.mappers;

import com.devsu.transactions.domain.model.Report;
import com.devsu.transactions.infrastructure.web.dto.model.ReportDTO;

import java.util.List;

public class WebReportMapper {
    private WebReportMapper() {
    }

    public static ReportDTO toDTO(Report report) {
        return ReportDTO.builder()
                .account(WebAccountMapper.toDTO(report.getAccount()))
                .transactions(WebTransactionMapper.toDTOList(report.getTransactions()))
                .build();
    }

    public static List<ReportDTO> toDTOList(List<Report> reports) {
        return reports.stream()
                .map(WebReportMapper::toDTO)
                .toList();
    }
}
