package com.microservice.student.exceptions;

public class StudentEmailAlreadyExistException extends RuntimeException {

    public StudentEmailAlreadyExistException(String message) {
        super(message);
    }

}
