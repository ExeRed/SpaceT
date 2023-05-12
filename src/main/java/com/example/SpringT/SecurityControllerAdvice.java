package com.example.SpringT;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SecurityControllerAdvice {

    @ExceptionHandler(SecurityException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public SecurityResponse handleSecurityException(SecurityException se) {
        SecurityResponse response = new SecurityResponse(se.getMessage());
        return response;
    }

    @ExceptionHandler(SecurityException2.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static SecurityResponse handleSecurityException1(SecurityException2 se) {
        SecurityResponse response1 = new SecurityResponse(se.getMessage());
        return response1;
    }

}