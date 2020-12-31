package com.praticaltraining.rsndm.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BaseException extends RuntimeException {
    private String error = "General error";

    private Integer status = 400;

    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String error, String message) {
        super(message);
        this.error = error;
    }
}
