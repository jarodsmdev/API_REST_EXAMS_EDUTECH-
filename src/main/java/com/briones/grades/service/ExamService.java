package com.briones.grades.service;

import com.briones.grades.model.Exam;
import com.briones.grades.repository.ExamRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @Transactional
public class ExamService implements ICrudService<Exam>{
    
    @Autowired
    private ExamRepository examRepository;
    
    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam findById(Long id) {
        return examRepository.findById(id).get();
    }

    @Override
    public Exam save(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public void delete(Long id) {
        examRepository.deleteById(id);
    }
}
