package com.development.account.infraestructure.input.adapter.rest.impl;

import com.development.account.application.input.port.ReportInputPort;
import com.development.account.domain.dto.Report.AccountStatementDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
@Slf4j
public class ReportController {

    private final ReportInputPort reportInputPort;

    @GetMapping(params = { "clientId", "startDate", "endDate"})
    public AccountStatementDto getAccountStatementByClientAndDates(
            @RequestParam
            @NotBlank(message = "Client Id must not be empty.")
            @Pattern(regexp = "^[0-9A-Za-z]+$", message = "Client Id must match pattern {regexp}")
            @Size(max = 30, message = "Client Id must be maximum {max} characters")
            String clientId,
            @RequestParam
            //@NotBlank(message = "Start date must not be empty.")
            //@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}+$", message = "Client Id must match pattern {regexp}")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate startDate,
            @RequestParam
            //@NotBlank(message = "End date must not be empty.")
            //@Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}+$", message = "Client Id must match pattern {regexp}")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate endDate) {
        log.info("|-> Started getAccountStatement clientId: {}, startDate: {}, endDate: {}", clientId, startDate, endDate);

        return reportInputPort.generateAccountStatement(clientId, startDate, endDate);
    }
}
