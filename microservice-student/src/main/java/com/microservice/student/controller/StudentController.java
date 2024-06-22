package com.microservice.student.controller;

import com.microservice.student.controller.dto.ApiResponse;
import com.microservice.student.entity.dto.StudentDto;
import com.microservice.student.service.StudentServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/student")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class StudentController {

    private final StudentServiceImp studentServiceImp;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public ApiResponse<List<StudentDto>> index() {
        Iterable<StudentDto> studentDtoIterable = studentServiceImp.index();
        List<StudentDto> studentDtoList = StreamSupport.stream(studentDtoIterable.spliterator(), false)
            .collect(Collectors.toList());
        return new ApiResponse<>("success", "Students list", studentDtoList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public ApiResponse<StudentDto> show(@PathVariable Long id) {
        StudentDto studentDto = studentServiceImp.show(id);
        return new ApiResponse<>("success", "Student found", studentDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ApiResponse<StudentDto> store(@Valid @RequestBody StudentDto studentDto) {
        StudentDto newStudent = studentServiceImp.store(studentDto);
        return new ApiResponse<>("created", "Successfully created student", newStudent);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ApiResponse<StudentDto> update(@Valid @PathVariable Long id, @RequestBody StudentDto studentDto) {
        StudentDto newStudent = studentServiceImp.update(id, studentDto);
        return new ApiResponse<>("success", "Successfully updated student", newStudent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void destroy(@PathVariable Long id) {
        studentServiceImp.destroy(id);
    }

    @GetMapping("/course/{courseId}")
    @Transactional(readOnly = true)
    public ApiResponse<List<StudentDto>> showByCourseId(@PathVariable Long courseId) {
        List<StudentDto> studentDtoList = studentServiceImp.showByCourseId(courseId);
        return new ApiResponse<>("success", "Course students", studentDtoList);
    }

}
