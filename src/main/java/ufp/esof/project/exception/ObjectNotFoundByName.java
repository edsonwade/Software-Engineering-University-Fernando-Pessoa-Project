package ufp.esof.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ObjectNotFoundByName extends RuntimeException{
    public ObjectNotFoundByName(String message) {
        super(message);
    }
}
