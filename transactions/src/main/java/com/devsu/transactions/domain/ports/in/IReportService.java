package com.devsu.transactions.domain.ports.in;

import com.devsu.transactions.domain.model.Report;

import java.util.List;

public interface IReportService {

    Report getReport(Long idAccount, String range);
    List<Report> getReport(String idPerson, String range);

}
