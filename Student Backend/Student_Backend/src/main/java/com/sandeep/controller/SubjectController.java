package com.sandeep.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandeep.entity.Student;
import com.sandeep.entity.Subject;
import com.sandeep.service.StudentService;
import com.sandeep.service.SubjectService;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

	private final SubjectService subjectService;
    private final StudentService studentService;

    @Autowired
    public SubjectController(SubjectService subjectService, StudentService studentService) {
        this.subjectService = subjectService;
        this.studentService = studentService;
    }

//    @PostMapping("/save")
//    public ResponseEntity<Subject> saveSubject(@Valid @RequestBody Subject subject) {
//        Subject savedSubject = subjectService.saveSubject(subject);
//        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
//    }
    
    @PostMapping("/save")
    public ResponseEntity<Subject> saveSubject(@Valid @RequestBody Subject subject) {
        // Retrieve the student object from the database
        Student student = studentService.getStudentById(subject.getStudent().getId());
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        // Set the student in the subject entity
        subject.setStudent(student);

        Subject savedSubject = subjectService.saveSubject(subject);
        return new ResponseEntity<>(savedSubject, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable Long id) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject != null) {
            return new ResponseEntity<>(subject, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Subject> updateSubject(@PathVariable Long id, @RequestBody Subject updatedSubject) {
        Subject subject = subjectService.getSubjectById(id);
        if (subject != null) {
            subject.setName(updatedSubject.getName());
            subject.setCode(updatedSubject.getCode());
            subject.setTheoryOrPractical(updatedSubject.getTheoryOrPractical());
            subject.setStudent(updatedSubject.getStudent());

            Subject updatedSubjectEntity = subjectService.updateSubject(id, subject);
            return new ResponseEntity<>(updatedSubjectEntity, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        subjectService.deleteSubject(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
