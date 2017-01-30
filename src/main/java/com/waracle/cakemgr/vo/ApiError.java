package com.waracle.cakemgr.vo;

import org.springframework.http.HttpStatus;

@SuppressWarnings("unused")
public final class ApiError {

    private final String requestPath;
    private final HttpStatus status;
    private final String errorMessage;

    public ApiError(String requestPath, HttpStatus status, String errorMessage) {
        this.requestPath = requestPath;
        this.status = status;
        this.errorMessage = errorMessage;
    }

    public String getRequestPath() {
        return requestPath;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "ApiError{" +
                "requestPath='" + requestPath + '\'' +
                ", status=" + status +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}
