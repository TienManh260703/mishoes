package com.mishoes.exceptions;

public class DataNotFoundException extends  RuntimeException{
    public DataNotFoundException(String message){
        super(message);
    }
}
