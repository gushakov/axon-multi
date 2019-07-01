package com.github.axonmulti.common.exception;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @Value
    public static class RestError {
        private final String message;
        private final String cause;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public RestError handleError(Exception ex){
        log.error("[Rest Error] Error: " + ex.getCause());
        ex.printStackTrace();
        return new RestError(ex.getMessage(), ExceptionUtils.getRootCauseMessage(ex));
    }

}
