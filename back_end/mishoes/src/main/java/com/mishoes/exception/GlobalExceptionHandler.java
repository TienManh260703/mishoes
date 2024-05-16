package com.mishoes.exception;

import com.mishoes.dto.response.APIResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handlerRuntimeException(RuntimeException exception) {
        return ResponseEntity.badRequest().body(
                exception.getMessage() + "Lỗi khác "
        );
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<?> handlerAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(
                        APIResponse.builder()
                                .code(errorCode.getCode())
                                .message(exception.getMessage() + "App")
                                .build());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerValidation(
            MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(
                exception.getFieldError().getDefaultMessage());
    }

    // Chưa bắt được
    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<?> handlerIllegalStateException(
            IllegalStateException exception) {
        return ResponseEntity.badRequest().body(
                exception.getMessage() + "Lỗi IllegalStateException");
    }

    @ExceptionHandler(value = DataNotFoundException.class)
    public ResponseEntity<?> handlerDataNotFountException(DataNotFoundException exception) {
        return ResponseEntity.badRequest().body(
                exception.getMessage());
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return ResponseEntity.badRequest().body(
                "Error SQL : " + exception.getMessage()
        );
    }

    @ExceptionHandler(value = DataAlreadyExistsException.class)
    public ResponseEntity<?> handleDataAlreadyExistException(DataAlreadyExistsException exception) {
        return ResponseEntity.badRequest().body(
                exception.getMessage()
        );
    }

    @ExceptionHandler(value = ParseException.class)
    public ResponseEntity<?> handleParseException(ParseException exception) {
        return ResponseEntity.badRequest().body("Parse token fail");
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException exception) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;
        return ResponseEntity.status(errorCode.getStatusCode()).body(
                APIResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );
    }
    @ExceptionHandler(value = AuthenticationServiceException.class)
    public ResponseEntity<?> handleAuthenticationServiceException(AuthenticationServiceException exception) {
        ErrorCode errorCode = ErrorCode.TOKEN_INVALID;
        return ResponseEntity.status(errorCode.getStatusCode()).body(
                APIResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build()
        );
    }
}
