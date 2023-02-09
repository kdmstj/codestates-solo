package com.codestates.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    private List<FieldError> fieldErrors;


    @AllArgsConstructor
    @Getter
    public static class FieldError{
        private String field;
        private Object rejectedValue;
        private String reason;

    }


}
