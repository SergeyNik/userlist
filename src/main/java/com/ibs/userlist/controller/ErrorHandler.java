package com.ibs.userlist.controller;

import com.ibs.userlist.dto.error.ErrorResponse;
import com.ibs.userlist.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Обработка глобальных исключений
 */
@Slf4j
@ControllerAdvice
@RestController
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<ErrorResponse> handleNotFoundException(RuntimeException e) {
        log.error(e.getMessage(), e);
        return buildResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAnyOtherException(Exception e) {
        log.error(e.getMessage(), e);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Непредвиденная ошибка на сервере");
    }

    private static ResponseEntity<ErrorResponse> buildResponse(HttpStatus status, String message) {
        return new ResponseEntity<>(ErrorResponse.builder()
                .errorCode(status.value())
                .message(message)
                .build(), status);
    }
}
