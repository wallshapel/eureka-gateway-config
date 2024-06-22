package com.microservice.course.client;

import com.microservice.course.client.dto.StudentDto;
import com.microservice.course.controller.dto.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-student", url = "http://localhost:8080/api/v1/student") // name corresponde al nombre que se especificó en el application.yml del ms student. Lo mismo aplica para el puerto, pero si se usa un gateway entonces se usa el del gateway. /api/v1/student es el RequestMapping de ese ms
public interface StudentClient {

    @GetMapping("/course/{courseId}") // /course/{courseId} este endpoint debe estar en el ms student
    ApiResponse<List<StudentDto>> showByCourseId(@PathVariable("courseId") Long courseId);

}
