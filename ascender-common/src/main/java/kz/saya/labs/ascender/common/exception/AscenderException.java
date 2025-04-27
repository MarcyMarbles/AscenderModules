package kz.saya.labs.ascender.common.exception;

/**
 * Base exception class for all Ascender application exceptions.
 */
public class AscenderException extends RuntimeException {

    public AscenderException(String message) {
        super(message);
    }

    public AscenderException(String message, Throwable cause) {
        super(message, cause);
    }
}