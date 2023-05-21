package com.sandeep.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeep.entity.Student;
import com.sandeep.repository.StudentRepository;
import com.sandeep.service.StudentService;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
    
    @Override
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
            existingStudent.setAddress(updatedStudent.getAddress());
            existingStudent.setMobile(updatedStudent.getMobile());
            existingStudent.setSubjects(updatedStudent.getSubjects());
            return studentRepository.save(existingStudent);
        }
        return null;
    }
}
