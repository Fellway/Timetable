package com.fryzjer.fryzjer.exception;

public class ApiError {
    private Integer status;
    private String source;
    private Integer code;
    private String message;

    public ApiError(final Integer status, final String source, final String message, final Integer code){
        this.status = status;
        this.source = source;
        this.code = code;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "status=" + status +
                ", source='" + source + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
