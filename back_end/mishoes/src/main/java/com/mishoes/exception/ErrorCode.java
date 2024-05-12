package com.mishoes.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    SUCCESS(1000, "Success", HttpStatus.OK),
    UNCATEGORIZED(9999, "Uncategorized error !", HttpStatus.INTERNAL_SERVER_ERROR),
    CATEGORY_EXISTED(1001, "Category existed !", HttpStatus.BAD_REQUEST),
    CATEGORY_INVALID(1002, "Category invalid", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1003, "Unauthenticated", HttpStatus.UNAUTHORIZED),// 401
    UNAUTHORIZED(1004 , "You do not have permission", HttpStatus.FORBIDDEN),// 403
    NOT_EXIST(1005, "Not exist", HttpStatus.NOT_FOUND);
    private int code;
    private String message;
    private HttpStatus statusCode;
}
