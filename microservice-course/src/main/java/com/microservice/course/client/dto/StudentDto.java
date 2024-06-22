package com.microservice.course.client.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {

    private String name;
    private String lastname;
    private String email;

}
