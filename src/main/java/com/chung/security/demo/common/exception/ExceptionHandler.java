package com.chung.security.demo.common.exception;

import com.chung.security.demo.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        LOGGER.error("Advice 내 ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorType(httpStatus.getReasonPhrase())
                .msg("에러 발생")
                .build(), responseHeaders, httpStatus.value());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> customExceptionHandler(CustomException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        LOGGER.error("Advice 내 customExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorType(e.getHttpStatusType())
                .msg(e.getMessage())
                .build(), responseHeaders, e.getHttpStatusCode());
    }

}
