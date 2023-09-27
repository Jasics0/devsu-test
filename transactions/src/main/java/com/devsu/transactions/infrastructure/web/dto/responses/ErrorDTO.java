package com.devsu.transactions.infrastructure.web.dto.responses;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorDTO {
    private String code;
    private String message;
}
