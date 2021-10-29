package com.jadesenvovimento.holeriteweb.exceptions;


public class UsuarioNotFountException extends RuntimeException{

    private static final Long serialVersionUID = 1L;

    public UsuarioNotFountException(String exception){
        super(exception);
    }
}
