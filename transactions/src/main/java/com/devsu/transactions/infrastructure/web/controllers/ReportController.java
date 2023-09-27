package com.devsu.transactions.infrastructure.web.controllers;

import com.devsu.transactions.domain.ports.in.IReportService;
import com.devsu.transactions.infrastructure.web.dto.model.ReportDTO;
import com.devsu.transactions.infrastructure.web.dto.responses.ResponseDTO;
import com.devsu.transactions.infrastructure.web.mappers.WebReportMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final IReportService reportService;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getReports(@RequestParam(required = true) String range,
                                                  @RequestParam(required = true) String id) {

        List<ReportDTO> listReport= WebReportMapper.toDTOList(reportService.getReport(id, range));

        return new ResponseEntity<>(new ResponseDTO(listReport, "Report found successfully"), HttpStatus.OK);
    }

}
