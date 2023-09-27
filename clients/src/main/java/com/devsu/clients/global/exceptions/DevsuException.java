package com.devsu.clients.global.exceptions;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Data
public class DevsuException extends RuntimeException {
    @Getter
    public enum DevsuError {

        CLIENT_NOT_FOUND("Devsu-404", "Client not found", HttpStatus.NOT_FOUND),
        CLIENT_ALREADY_EXISTS("Devsu-409", "Client already exists", HttpStatus.CONFLICT),
        CLIENT_NOT_CREATED("Devsu-500", "Client not created", HttpStatus.INTERNAL_SERVER_ERROR),
        CLIENT_NOT_UPDATED("Devsu-500", "Client not updated", HttpStatus.INTERNAL_SERVER_ERROR),
        CLIENT_NOT_DELETED("Devsu-500", "Client not deleted", HttpStatus.INTERNAL_SERVER_ERROR),
        CLIENT_DATA_REQUIRED("Devsu-400", "Missing client fields", HttpStatus.BAD_REQUEST);

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
