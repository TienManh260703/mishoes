package com.mishoes.exception;

import com.mishoes.dto.response.APIResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                exception.getMessage() + "Lỗi khác "
        );
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handlerAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.badRequest().body(APIResponse.builder()
                .code(errorCode.getCode())
                .message(exception.getMessage() + "App")
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidation(
            MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(
                exception.getFieldError().getDefaultMessage());
    }

    // Chưa bắt được
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handlerIllegalStateException(
            IllegalStateException exception) {
        return ResponseEntity.badRequest().body(
                exception.getMessage() + "Lỗi IllegalStateException");
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<?> handlerDataNotFountException(DataNotFoundException exception) {
        return ResponseEntity.badRequest().body(
                exception.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(
                "The name already exists"
        );
    }

    @ExceptionHandler(DataAlreadyExistsException.class)
    public ResponseEntity<?> handleDataAlreadyExistException(DataAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(
                exception.getMessage()
        );
    }

    @ExceptionHandler(ParseException.class)
    public ResponseEntity<?> handleParseException (ParseException exception){
        return ResponseEntity.badRequest().body("Parse token fail");
    }

    @ExceptionHandler(AccessDeniedException.class)
    public  ResponseEntity<?> handleAccessDeniedException (AccessDeniedException exception){
        return ResponseEntity.badRequest().body(
                APIResponse.builder()
                .code(1004)
                .message("You do not have access")
                .build()
        );
    }

}
