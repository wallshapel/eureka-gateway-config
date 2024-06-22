package com.microservice.student.exceptions;

import com.microservice.student.exceptions.dto.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> studentNotFoundException(StudentNotFoundException exception) {
        ErrorMessage message = new ErrorMessage("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    @ExceptionHandler(StudentEmailAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorMessage> studentEmailAlreadyExistException(StudentEmailAlreadyExistException exception) {
        ErrorMessage message = new ErrorMessage("error", exception.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(message);
    }

}
