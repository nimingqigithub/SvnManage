package com.assassin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Administrator on 2016/10/24.
 */
@ControllerAdvice
public class RealEstateExceptionAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ExceptionHandler(RealEstateException.class)
    @ResponseBody
    public RealEstateExceptionErrorInfo handleIllegal(RealEstateException e) {
        return new RealEstateExceptionErrorInfo("Bad request", e);
    }
}
