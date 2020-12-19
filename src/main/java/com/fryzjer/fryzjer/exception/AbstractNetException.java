package com.fryzjer.fryzjer.exception;

public class AbstractNetException extends RuntimeException {
    private static final int DEFAULT_ERROR_CODE = -1;
    private final int code;

    public AbstractNetException(final String message) {
        super(message);
        this.code = DEFAULT_ERROR_CODE;
    }

    public AbstractNetException(final String message, final int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }


}
