package ufp.esof.project.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ufp.esof.project.exception.ObjectExceptionResponse;
import ufp.esof.project.exception.ObjectNotFoundById;
import ufp.esof.project.exception.RequiredObjectIsNullException;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@RestController
@ControllerAdvice
public class ObjectEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public final ResponseEntity<ObjectExceptionResponse> handleAll(
            Exception e, WebRequest webRequest) {
        ObjectExceptionResponse objectExceptionResponse = new ObjectExceptionResponse(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                webRequest.getDescription(false));

        return new ResponseEntity<>(objectExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ObjectNotFoundById.class)
    public final ResponseEntity<ObjectExceptionResponse> handleBadRequest(
            Exception e, WebRequest webRequest) {
        ObjectExceptionResponse objectExceptionResponse = new ObjectExceptionResponse(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                webRequest.getDescription(false));

        return new ResponseEntity<>(objectExceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = RequiredObjectIsNullException.class)
    public final ResponseEntity<ObjectExceptionResponse> handleBadRequestException(
            Exception e, WebRequest webRequest) {
        ObjectExceptionResponse objectExceptionResponse = new ObjectExceptionResponse(
                e.getMessage(),
                ZonedDateTime.now(ZoneId.of("Z")),
                webRequest.getDescription(false));

        return new ResponseEntity<>(objectExceptionResponse, HttpStatus.BAD_REQUEST);
    }



}
