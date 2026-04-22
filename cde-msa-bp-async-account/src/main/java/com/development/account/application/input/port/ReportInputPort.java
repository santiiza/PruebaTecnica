package com.development.account.application.input.port;

import com.development.account.domain.dto.Report.AccountStatementDto;

import java.time.LocalDate;

public interface ReportInputPort {
    AccountStatementDto generateAccountStatement(String clientId, LocalDate startDate, LocalDate endDate);
}
