package com.microservice.student.repository;

import com.microservice.student.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    List<Student> findAllByCourseId(Long courseId); // Función para obtener todos los estudiantes por el id de un curso dentro de la misma tabla de students
    boolean existsByEmail(String email); // Función que nos ayuda a validar que los emails de los estudiantes sean únicos

}
