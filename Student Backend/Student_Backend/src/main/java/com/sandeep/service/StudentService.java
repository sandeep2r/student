package com.sandeep.service;

import java.util.List;

import com.sandeep.entity.Student;

public interface StudentService {
    Student saveStudent(Student student);
    Student getStudentById(Long id);
    List<Student> getAllStudents();
    void deleteStudent(Long id);
    Student updateStudent(Long id, Student updatedStudent);
}

