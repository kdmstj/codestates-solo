package com.codestates.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    TODO_NOT_FOUND(405, "TODO_NOT_FOUND"),
    ;


    private int status;
    private String message;

    ErrorCode(int code, String message){
        this.status = code;
        this.message = message;
    }
}
