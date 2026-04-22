package com.development.account.domain.dto.Report;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AccountStatementDto {
    ClientDto client;
    DateRangeDto dateRange;
    List<AccountReportDto> accounts;
}
