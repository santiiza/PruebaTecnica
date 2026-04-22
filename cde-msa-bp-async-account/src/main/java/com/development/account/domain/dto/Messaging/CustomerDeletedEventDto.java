package com.development.account.domain.dto.Messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDeletedEventDto {
    String eventId;
    Instant occurredAt;
    String clientId;
}
