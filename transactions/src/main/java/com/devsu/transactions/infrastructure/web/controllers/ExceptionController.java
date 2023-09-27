package com.devsu.transactions.infrastructure.web.controllers;


import com.devsu.transactions.global.exceptions.DevsuException;
import com.devsu.transactions.infrastructure.web.dto.responses.ErrorDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(DevsuException.class)
    public ResponseEntity<  ErrorDTO> handleDevsuExceptions(DevsuException ex) {


        ErrorDTO errorDTO = ErrorDTO.builder()
                .code(ex.getDevsuError().getCode())
                .message(ex.getMessage())
                .build();


        return new ResponseEntity<>(errorDTO, ex.getDevsuError().getHttpStatus());
    }
}
