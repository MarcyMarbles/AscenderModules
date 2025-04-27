package kz.saya.labs.ascender.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when a request contains invalid data.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends AscenderException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}