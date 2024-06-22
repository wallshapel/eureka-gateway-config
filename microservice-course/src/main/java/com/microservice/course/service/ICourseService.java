package com.microservice.course.service;

import com.microservice.course.entity.dto.CourseDto;
import com.microservice.course.http.response.StudentsByCourseDto;

public interface ICourseService {

    Iterable<CourseDto> index();
    CourseDto show(Long id);
    CourseDto store(CourseDto studentDto);
    CourseDto update(Long id, CourseDto studentDto);
    void destroy(Long id);

    StudentsByCourseDto showByCourseId(Long courseId);

}
