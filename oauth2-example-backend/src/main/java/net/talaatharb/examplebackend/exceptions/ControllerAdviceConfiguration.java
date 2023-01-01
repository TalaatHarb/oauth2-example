package net.talaatharb.examplebackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceConfiguration {

    ControllerAdviceConfiguration() {
    }

    @ExceptionHandler(TodoNotFoundException.class)
    public ResponseEntity<Object> exception(final TodoNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
