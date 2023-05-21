package com.sandeep.service;

import java.util.List;

import com.sandeep.entity.Subject;

public interface SubjectService {
    Subject saveSubject(Subject subject);
    Subject getSubjectById(Long id);
    List<Subject> getAllSubjects();
    void deleteSubject(Long id);
    Subject updateSubject(Long id, Subject updatedSubject);
}


