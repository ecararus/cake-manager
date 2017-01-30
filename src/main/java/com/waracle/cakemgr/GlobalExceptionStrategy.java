package com.waracle.cakemgr;

import com.waracle.cakemgr.vo.ApiError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionStrategy {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionStrategy.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiError handleException(HttpServletRequest request, Exception ex) {
        logger.error("Request: " + request + " raised " + ex);
        return new ApiError(request.getRequestURL().toString(), INTERNAL_SERVER_ERROR, "Internal server error occurred");
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    public ApiError handleBadRequest(HttpServletRequest request, Exception ex) {
        logger.error("Request: " + request + " raised " + ex);
        return new ApiError(request.getRequestURL().toString(), BAD_REQUEST, "Wrong parameters");
    }

}
