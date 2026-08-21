package com.rev.rest.springg.service;

import com.rev.rest.springg.exceptions.StudentNotFoundException;
import com.rev.rest.springg.model.Student;
import com.rev.rest.springg.repositories.StudentRespository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class StudentService {
    private final StudentRespository studentRepository;

    public StudentService(StudentRespository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(int id){
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
    }

    public Student addStudent(Student student){
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student updatedStudent) {
        Student existingStudent =  studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        existingStudent.setName(updatedStudent.getName());
        existingStudent.setEmail(updatedStudent.getEmail());
        existingStudent.setCourse(updatedStudent.getCourse());
        studentRepository.save(existingStudent);
        return existingStudent;
    }

    public List<Student> deleteStudent(int id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return studentRepository.findAll();
        } else {
            throw new StudentNotFoundException(id);
        }
    }



}
