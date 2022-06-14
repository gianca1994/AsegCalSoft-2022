package com.gianca1994.AsegCalSoft2022.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class EntityNotValidException extends RuntimeException {
    public EntityNotValidException() {
        super();
    }

    public EntityNotValidException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotValidException(String message) {
        super(message);
    }

    public EntityNotValidException(Throwable cause) {
        super(cause);
    }
}
