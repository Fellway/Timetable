package com.fryzjer.fryzjer.controller;

import com.fryzjer.fryzjer.exception.ApiError;
import com.fryzjer.fryzjer.exception.StdBadRequestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public abstract class ExceptionHandlerController {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(code = BAD_REQUEST)
    public ApiError handleBadRequestException(final StdBadRequestException e, final HttpServletRequest request) {
        return new ApiError(BAD_REQUEST.value(), getSource(request), e.getMessage(), e.getCode());
    }

    private String getSource(final HttpServletRequest request) {
        String source;
        try {
            source = request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString();
        } catch (Exception e) {
            source = "Unknown source";
        }
        return source;
    }
}
