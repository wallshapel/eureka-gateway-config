package com.microservice.student.service;

import com.microservice.student.entity.dto.StudentDto;

import java.util.List;

public interface IStudentService {

    Iterable<StudentDto> index();
    StudentDto show(Long id);
    StudentDto store(StudentDto studentDto);
    StudentDto update(Long id, StudentDto studentDto);
    void destroy(Long id);

    List<StudentDto> showByCourseId(Long courseId);

}
