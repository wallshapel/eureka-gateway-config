package com.microservice.student.service;

import com.microservice.student.components.Mapper;
import com.microservice.student.entity.dto.StudentDto;
import com.microservice.student.entity.Student;
import com.microservice.student.exceptions.StudentNotFoundException;
import com.microservice.student.helpers.UniqueEmailValidator;
import com.microservice.student.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class StudentServiceImp implements IStudentService {

    private final StudentRepository studentRepository;
    private final Mapper mapper;
    private final UniqueEmailValidator uniqueEmailValidator;

    @Override
    public Iterable<StudentDto> index() {
        Iterable<Student> studentIterable = studentRepository.findAll();
        // Convertimos un Iterable a una lista
        List<Student> studentList = StreamSupport.stream(studentIterable.spliterator(), false)
            .toList();
        return studentList.stream()
                .map(student -> mapper.toDto(student, StudentDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto show(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student is not available"));
        return mapper.toDto(student, StudentDto.class);
    }

    @Override
    public StudentDto store(StudentDto studentDto) {
        uniqueEmailValidator.validateUniqueEmail(studentDto.getEmail());
        studentRepository.save(mapper.toEntity(studentDto, Student.class));
        return studentDto;
    }

    @Override
    public StudentDto update(Long id, StudentDto studentDto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student is not available"));
        uniqueEmailValidator.validateUniqueEmail(studentDto.getEmail());
        student.setName(studentDto.getName());
        student.setEmail(studentDto.getEmail());
        student.setLastName(studentDto.getLastname());
        student.setCourseId(studentDto.getCourseId());
        Student updatedStudent = studentRepository.save(student);
        return mapper.toDto(updatedStudent, StudentDto.class);
    }

    @Override
    public void destroy(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student is not available"));
        studentRepository.deleteById(id);
    }

    public List<StudentDto> showByCourseId(Long courseId) {
        List<Student> studentList = studentRepository.findAllByCourseId(courseId);
        List<StudentDto> studentDtoList = mapper.toDtoList(studentList, StudentDto.class);
        return studentDtoList;
    }

}