package com.example.b2win.global.exception;

import com.example.b2win.global.error.ErrorCode;
import com.example.b2win.global.error.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.example.b2win.global.error.ErrorResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        log.error("handleMethodArgumentNotValidException throw CustomException : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.NOTEQUAL_INPUT_PASSWORD);
    }

    @ExceptionHandler(value = {ConstraintViolationException.class, MethodArgumentNotValidException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(Exception e){
        log.error("handleMethodArgumentNotValidException throw CustomException : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.valueOf("INVALID_INPUT_VALUE"));
    }


    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        log.error("handleHttpRequestMethodNotSupportedException throw CustomException : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(ErrorCode.valueOf("METHOD_NOT_ALLOWED"));

    }
}
