package com.jadesenvovimento.holeriteweb.exceptions.handerExceptions;

import com.jadesenvovimento.holeriteweb.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class ReponseEntityCustomized extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<MessageExceptions> allExpcetions(Exception ex, WebRequest request){
        MessageExceptions msExcecption = new MessageExceptions(new Date(), HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getDescription(false));
        return new ResponseEntity<>(msExcecption, HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @ExceptionHandler(TokenNotFoundExcpetion.class)
    public final ResponseEntity<MessageExceptions> TokenNotFound(Exception ex, WebRequest request){
        MessageExceptions msExcecption = new MessageExceptions(new Date(), HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(msExcecption, HttpStatus.BAD_REQUEST );
    }

    @ExceptionHandler(UsuarioNotFountException.class)
    public final ResponseEntity<MessageExceptions> UsuarioNotFount(Exception ex, WebRequest request){
        MessageExceptions msExcecption = new MessageExceptions(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(msExcecption, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(OrgaoNotFount.class)
    public final ResponseEntity<MessageExceptions> OrgaoNotFound(Exception ex, WebRequest request){
        MessageExceptions msExcecption = new MessageExceptions(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(msExcecption, HttpStatus.NOT_FOUND );
    }

    @ExceptionHandler(FuncionarioException.class)
    public final ResponseEntity<MessageExceptions> FuncionarioNotFound(Exception ex){
        MessageExceptions msExcption = new MessageExceptions(new Date(), HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(msExcption, HttpStatus.NOT_FOUND);
    }
}
