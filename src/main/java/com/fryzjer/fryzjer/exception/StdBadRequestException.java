package com.fryzjer.fryzjer.exception;

public class StdBadRequestException extends AbstractNetException {
    private final String title;

    public StdBadRequestException(final String message, final Integer code) {
        super(message);
        title = "";
    }

    public StdBadRequestException(final IApiError apiError) {
        super(apiError.getMessage(), apiError.getErrorCode());
        title = "";
    }
}
