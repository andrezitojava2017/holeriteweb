package com.jadesenvovimento.holeriteweb.exceptions;

public class FuncionarioException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public FuncionarioException(String exception){
        super(exception);
    }

}
