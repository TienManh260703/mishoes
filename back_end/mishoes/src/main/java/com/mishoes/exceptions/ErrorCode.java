package com.mishoes.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ErrorCode {
    SUCCESS(1000, "Success") ,
    CATEGORY_EXISTED(1001, "Category existed !"),
    CATEGORY_INVALID (1002, "Category invalid"),
    UNCATEGORIZED(9999 , "Uncategorized error !"),
    UNAUTHENTICATED(1003, "Unauthenticated")
    ;
    private  int code;
    private String message;
}
