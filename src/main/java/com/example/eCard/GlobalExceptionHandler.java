package com.example.eCard;

import com.example.eCard.exception.BusinessException;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @SneakyThrows
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleGenericException(BusinessException ex) {
        String message = ex.getMessage();
        logger.info("[TECH] [ERROR] Exception: ", ex);
        return handleException(ex, null);
    }

}
