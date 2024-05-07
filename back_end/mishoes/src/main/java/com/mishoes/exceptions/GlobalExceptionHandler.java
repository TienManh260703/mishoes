package com.mishoes.exceptions;

import com.mishoes.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler (Exception.class)
    public ResponseEntity<?> handlerRuntimeException (RuntimeException exception){
        return ResponseEntity.badRequest().body(
                exception.getMessage()
        );
    }

    @ExceptionHandler (AppException.class)
    public ResponseEntity<?> handlerAppException (AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.badRequest().body(APIResponse.builder()
                .code(errorCode.getCode())
                .message(exception.getMessage())
                .build());
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidation (
            MethodArgumentNotValidException exception){
        return ResponseEntity.badRequest().body(
                exception.getFieldError().getDefaultMessage());
    }
// Chưa bắt được
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handlerIllegalStateException (
            IllegalStateException exception){
        return ResponseEntity.badRequest().body(
                exception.getMessage());
    }


}
