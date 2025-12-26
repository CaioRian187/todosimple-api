package com.caiorian.todosimple.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor // Um construtor para todas as variaveis final
@JsonInclude(JsonInclude.Include.NON_NULL) // Significa que só ira incluir o que não for null nas variaveis criadas abaixo
public class ErrorResponse {
    private final int status;
    private final String message;
    private String stackTrace;
    private List<ValidationError> errors;

    @Getter
    @Setter
    @RequiredArgsConstructor
    private static class  ValidationError {
        private final String field;
        private final String message;    
    }


    public void addValidationError(String field, String message){
        if (Objects.isNull(errors)){
            this.errors = new ArrayList<>();
        }
        this.errors.add(new ValidationError(field, message));
    }



}
