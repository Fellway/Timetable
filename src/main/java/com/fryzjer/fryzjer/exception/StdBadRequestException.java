package com.fryzjer.fryzjer.exception;

public class StdBadRequestException extends AbstractNetException {

    public StdBadRequestException(final IApiError apiError) {
        super(apiError.getMessage(), apiError.getErrorCode());
    }
}
