package com.fryzjer.fryzjer.exception;

public class ForbiddenException extends AbstractNetException {

    public ForbiddenException(final IApiError apiError) {
        super(apiError.getMessage(), apiError.getErrorCode());
    }
}
