package com.development.account.domain.dto.Report;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DateRangeDto {
    LocalDate startDate;
    LocalDate endDate;
}
