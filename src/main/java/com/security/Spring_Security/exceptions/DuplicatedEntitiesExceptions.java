package com.security.Spring_Security.exceptions;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicatedEntitiesExceptions extends RuntimeException{
   
    public DuplicatedEntitiesExceptions(String message){
        super(message);
    }

   
}
