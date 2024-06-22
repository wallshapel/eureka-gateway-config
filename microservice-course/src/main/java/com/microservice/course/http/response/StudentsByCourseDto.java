package com.microservice.course.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.microservice.course.client.dto.StudentDto;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentsByCourseDto {

      private String courseName;
      private String teacher;

      @JsonProperty("students") // Con esto logramos que la llave en la respuesta sea students en lugar de studentDtoList
      private List<StudentDto> studentDtoList;

}
