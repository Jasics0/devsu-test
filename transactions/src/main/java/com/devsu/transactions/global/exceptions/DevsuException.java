package com.devsu.transactions.global.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
public class DevsuException extends RuntimeException {
    @Getter
    public enum DevsuError {

        ACCOUNT_NOT_FOUND("Devsu-404", "Account not found", HttpStatus.NOT_FOUND),
        ACCOUNT_ALREADY_EXISTS("Devsu-409", "Account already exists", HttpStatus.CONFLICT),
        ACCOUNT_NOT_CREATED("Devsu-500", "Account not created", HttpStatus.INTERNAL_SERVER_ERROR),
        ACCOUNT_NOT_UPDATED("Devsu-500", "Account not updated", HttpStatus.INTERNAL_SERVER_ERROR),
        ACCOUNT_NOT_DELETED("Devsu-500", "Account not deleted", HttpStatus.INTERNAL_SERVER_ERROR),
        ACCOUNT_NOT_ENOUGH_BALANCE("Devsu-400", "Account does not have sufficient balance", HttpStatus.BAD_REQUEST),
        ACCOUNT_DATA_REQUIRED("Devsu-400", "Missing account fields", HttpStatus.BAD_REQUEST),
        TRANSACTION_NOT_FOUND("Devsu-404", "Transaction not found", HttpStatus.NOT_FOUND),
        TRANSACTION_DATA_REQUIRED("Devsu-400", "Missing transaction fields", HttpStatus.BAD_REQUEST),
        TRANSACTION_ALREADY_EXISTS("Devsu-409", "Transaction already exists", HttpStatus.CONFLICT),
        TRANSACTION_NOT_CREATED("Devsu-500", "Transaction not created", HttpStatus.INTERNAL_SERVER_ERROR),
        TRANSACTION_NOT_UPDATED("Devsu-500", "Transaction not updated", HttpStatus.INTERNAL_SERVER_ERROR),
        TRANSACTION_NOT_DELETED("Devsu-500", "Transaction not deleted", HttpStatus.INTERNAL_SERVER_ERROR);


        private final String code;
        private final String message;
        private HttpStatus httpStatus;

        DevsuError(String code, String message, HttpStatus httpStatus) {
            this.code = code;
            this.message = message;
            this.httpStatus = httpStatus;
        }
    }

    private final DevsuError devsuError;
    private HttpStatus httpStatus;


    public DevsuException(DevsuError mcError) {
        super(mcError.message);
        this.devsuError = mcError;
    }

    public DevsuException(DevsuError mcError, String message) {
        super(message);
        this.devsuError = mcError;
    }

}
