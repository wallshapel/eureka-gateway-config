package com.microservice.student.helpers;

import com.microservice.student.exceptions.StudentEmailAlreadyExistException;
import com.microservice.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UniqueEmailValidator {

    private final StudentRepository studentRepository;

    public void validateUniqueEmail(String email) {
        if (studentRepository.existsByEmail(email)) {
            throw new StudentEmailAlreadyExistException("Email already exists");
        }
    }

}
