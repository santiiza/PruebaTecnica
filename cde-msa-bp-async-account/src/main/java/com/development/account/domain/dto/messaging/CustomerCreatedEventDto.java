package com.development.customer.domain.dto.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreatedEventDto {
    String eventId;
    Instant occurredAt;
    Long peId;
    String clId;
    String identification;
    Boolean status;
}
