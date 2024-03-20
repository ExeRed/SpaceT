package com.example.SpringT.securities;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SecurityControllerAdvice {

    @ExceptionHandler(SecurityException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleSecurityException(SecurityException se, Model model) {
        model.addAttribute("error", se.getMessage());
        return "error404";
    }

    @ExceptionHandler(SecurityException2.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public static SecurityResponse handleSecurityException1(SecurityException2 se) {
        SecurityResponse response1 = new SecurityResponse(se.getMessage());
        return response1;
    }


}