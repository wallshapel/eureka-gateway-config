package com.microservice.course.controller;

import com.microservice.course.controller.dto.ApiResponse;
import com.microservice.course.entity.dto.CourseDto;
import com.microservice.course.http.response.StudentsByCourseDto;
import com.microservice.course.service.CourseServiceImp;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/v1/course")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class CourseController {

    private final CourseServiceImp courseServiceImp;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public ApiResponse<List<CourseDto>> index() {
        Iterable<CourseDto> courseDtoIterable = courseServiceImp.index();
        List<CourseDto> courseDtoList = StreamSupport.stream(courseDtoIterable.spliterator(), false)
                .collect(Collectors.toList());
        return new ApiResponse<>("success", "Course list", courseDtoList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public ApiResponse<CourseDto> show(@PathVariable Long id) {
        CourseDto courseDto = courseServiceImp.show(id);
        return new ApiResponse<>("success", "Course found", courseDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public ApiResponse<CourseDto> store(@Valid @RequestBody CourseDto courseDto) {
        CourseDto newCourse = courseServiceImp.store(courseDto);
        return new ApiResponse<>("created", "Successfully created course", newCourse);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional
    public ApiResponse<CourseDto> update(@Valid @PathVariable Long id, @RequestBody CourseDto courseDto) {
        CourseDto newCourse = courseServiceImp.update(id, courseDto);
        return new ApiResponse<>("success", "Successfully updated course", newCourse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    public void destroy(@PathVariable Long id) {
        courseServiceImp.destroy(id);
    }

    @GetMapping("/students/course/{courseId}")
    @ResponseStatus(HttpStatus.OK)
    @Transactional(readOnly = true)
    public ApiResponse<StudentsByCourseDto> showByCourseId(@PathVariable Long courseId) {
        StudentsByCourseDto studentsByCourseDto = courseServiceImp.showByCourseId(courseId);
        return new ApiResponse<>("success", "Course students", studentsByCourseDto);
    }

}
