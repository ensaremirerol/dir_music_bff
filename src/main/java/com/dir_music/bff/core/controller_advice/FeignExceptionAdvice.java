package com.dir_music.bff.core.controller_advice;


import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class FeignExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FeignException.class)
    protected ResponseEntity<?> handleFeignException(FeignException e) {
        return ResponseEntity.status(e.status()).body(e.contentUTF8());
    }
}
