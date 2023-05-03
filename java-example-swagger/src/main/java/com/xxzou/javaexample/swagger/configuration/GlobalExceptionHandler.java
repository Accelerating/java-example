package com.xxzou.javaexample.swagger.configuration;

import com.xxzou.javaexample.swagger.common.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse handleException(MethodArgumentNotValidException ex, HttpServletRequest request){
        return ApiResponse.error(ex.getMessage());
    }

}
