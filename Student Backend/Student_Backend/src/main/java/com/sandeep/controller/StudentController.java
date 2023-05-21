package com.sandeep.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.sandeep.repository.StudentRepository;
import com.sandeep.repository.SubjectRepository;
import com.sandeep.service.StudentService;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

	private final StudentService studentService;

	private final SubjectRepository subjectRepository;

	private final StudentRepository studentRepository;

	@Autowired
	public StudentController(StudentService studentService, SubjectRepository subjectRepository,
			StudentRepository studentRepository) {
		this.studentService = studentService;
		this.subjectRepository = subjectRepository;
		this.studentRepository = studentRepository;
	}

	// working
	@PostMapping("/save")
	public ResponseEntity<?> saveStudent(@Valid @RequestBody Student student) {
		Student savedStudent = studentService.saveStudent(student);
//        for(Subject s : student.getSubjects()) {
//        	Subject sub = new Subject();
//        	sub.setName(s.getName());
//        	sub.setCode(s.getCode());
//        	sub.setTheoryOrPractical(s.getTheoryOrPractical());
//        	sub.setStudent(savedStudent);
//        	subjectRepository.save(sub);
//        }

		List<Subject> subjects = student.getSubjects();
		subjects.forEach(subject -> subject.setStudent(savedStudent));

		savedStudent.setSubjects(subjects);
		studentRepository.save(savedStudent);

		return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
	}

//working
	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		Student student = studentService.getStudentById(id);
		if (student != null) {
			return new ResponseEntity<>(student, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// working
	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	// need to resolve this
	/*
	 * "message":
	 * "A collection with cascade=\"all-delete-orphan\" was no longer referenced by the owning entity instance: com.sandeep.entity.Student.subjects; nested exception is org.hibernate.HibernateException: A collection with cascade=\"all-delete-orphan\" was no longer referenced by the owning entity instance: com.sandeep.entity.Student.subjects"
	 * 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
		Student student = studentService.getStudentById(id);
		if (student != null) {
			student.setName(updatedStudent.getName());
			student.setDateOfBirth(updatedStudent.getDateOfBirth());
			student.setAddress(updatedStudent.getAddress());
			student.setMobile(updatedStudent.getMobile());

			// Set the updated subjects
			List<Subject> updatedSubjects = updatedStudent.getSubjects();
			for (Subject subject : updatedSubjects) {
				subject.setStudent(student);
			}
			student.setSubjects(updatedSubjects);

			Student updatedStudentEntity = studentService.updateStudent(id, student);
			return new ResponseEntity<>(updatedStudentEntity, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// working
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		studentService.deleteStudent(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
