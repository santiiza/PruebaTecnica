package com.development.account.domain.dto.Report;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    String clientId;
    String name;
}
