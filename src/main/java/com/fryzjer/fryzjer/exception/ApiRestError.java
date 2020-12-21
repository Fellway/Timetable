package com.fryzjer.fryzjer.exception;

import org.springframework.http.HttpStatus;

public enum ApiRestError implements IApiError {

    USER_IS_NOT_AUTHENTICATED(2000, "Nie jesteś zalogowany! Proszę zaloguj się ponownie", HttpStatus.FORBIDDEN),

    RESERVATION_IS_NOT_ASSIGNED_TO_THIS_USER(4000, "Nie możesz anulować nie swojej rezerwacji!", HttpStatus.BAD_REQUEST),
    RESERVATION_DOESNT_EXISTS(4001, "Ta rezerwacja nie istnieje!", HttpStatus.BAD_REQUEST),
    RESERVATION_ALREADY_EXISTS(4002, "Ta rezerwacja już istnieje!", HttpStatus.BAD_REQUEST),
    ONE_RESERVATION_PER_DAY(4003, "Możesz mieć najwyżej jedną rezerwacje dziennie!", HttpStatus.BAD_REQUEST);

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
