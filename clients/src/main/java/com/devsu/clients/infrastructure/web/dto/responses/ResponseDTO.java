package com.devsu.clients.infrastructure.web.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO<T> {

    private T data;

    private String message;

    public ResponseDTO(String message) {
        this.message = message;
    }
}
