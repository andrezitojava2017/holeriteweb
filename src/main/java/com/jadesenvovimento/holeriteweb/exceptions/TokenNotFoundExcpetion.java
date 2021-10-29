package com.jadesenvovimento.holeriteweb.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class TokenNotFoundExcpetion extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public TokenNotFoundExcpetion(String exception){
        super(exception);
    }
}
