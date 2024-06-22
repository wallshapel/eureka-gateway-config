package com.microservice.course.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @SequenceGenerator(name = "course_seq_gen", sequenceName = "course_sequence", allocationSize = 1) // course_sequence: nombre de la secuencia en la db. course_seq_gen: nombre de la secuencia en spring boot. allocationSize: Desde donde comienza la secuencia
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank
    @Size(min = 2, max = 50)
    private String teacher;

}
