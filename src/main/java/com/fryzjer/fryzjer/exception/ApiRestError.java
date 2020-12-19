package com.fryzjer.fryzjer.exception;

import org.springframework.http.HttpStatus;

public enum ApiRestError implements IApiError {

    RESERVATION_IS_NOT_ASSIGNED_TO_THIS_USER(4000, "You cannot canceld not your reservation", HttpStatus.BAD_REQUEST);

    private final Integer errorCode;
    private final String message;
    private final HttpStatus httpStatus;

    ApiRestError(final Integer errorCode, final String message, final HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }
}
