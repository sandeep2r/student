package com.sandeep.serviceimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sandeep.entity.Subject;
import com.sandeep.repository.SubjectRepository;
import com.sandeep.service.SubjectService;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;

    @Autowired
    public SubjectServiceImpl(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public Subject saveSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    @Override
    public Subject getSubjectById(Long id) {
        return subjectRepository.findById(id).orElse(null);
    }

    @Override
    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }
    
    @Override
    public Subject updateSubject(Long id, Subject updatedSubject) {
        Subject existingSubject = subjectRepository.findById(id).orElse(null);
        if (existingSubject != null) {
            existingSubject.setName(updatedSubject.getName());
            existingSubject.setCode(updatedSubject.getCode());
            existingSubject.setTheoryOrPractical(updatedSubject.getTheoryOrPractical());
            existingSubject.setStudent(updatedSubject.getStudent());
            return subjectRepository.save(existingSubject);
        }
        return null;
    }
}
