package com.microservice.course.service;

import com.microservice.course.client.StudentClient;
import com.microservice.course.client.dto.StudentDto;
import com.microservice.course.components.Mapper;
import com.microservice.course.controller.dto.ApiResponse;
import com.microservice.course.entity.Course;
import com.microservice.course.entity.dto.CourseDto;
import com.microservice.course.exceptions.CourseNotFoundException;
import com.microservice.course.http.response.StudentsByCourseDto;
import com.microservice.course.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class CourseServiceImp implements ICourseService {

    private final CourseRepository courseRepository;
    private final Mapper mapper;
    private final StudentClient studentClient; // Necesario para conectarnos con el ms student

    @Override
    public Iterable<CourseDto> index() {
        Iterable<Course> courseIterable = courseRepository.findAll();
        // Convertimos un Iterable a una lista
        List<Course> courseList = StreamSupport.stream(courseIterable.spliterator(), false)
                .toList();
        return courseList.stream()
                .map(course -> mapper.toDto(course, CourseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CourseDto show(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course is not available"));
        return mapper.toDto(course, CourseDto.class);
    }

    @Override
    public CourseDto store(CourseDto courseDto) {
        courseRepository.save(mapper.toEntity(courseDto, Course.class));
        return courseDto;
    }

    @Override
    public CourseDto update(Long id, CourseDto courseDto) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course is not available"));
        course.setName(courseDto.getName());
        course.setTeacher(courseDto.getTeacher());
        Course updatedCourse = courseRepository.save(course);
        return mapper.toDto(updatedCourse, CourseDto.class);
    }

    @Override
    public void destroy(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new CourseNotFoundException("Course is not available"));
        courseRepository.deleteById(id);
    }

    @Override
    public StudentsByCourseDto showByCourseId(Long courseId) {
        CourseDto courseDto = show(courseId); // Verificamos que el curso exista. Esto arroja un 404 automático y controlado en caso que no exista
        ApiResponse<List<StudentDto>> response = studentClient.showByCourseId(courseId);
        return StudentsByCourseDto.builder()
                .courseName(courseDto.getName())
                .teacher(courseDto.getTeacher())
                .studentDtoList(response.getData())
                .build();
    }

}
