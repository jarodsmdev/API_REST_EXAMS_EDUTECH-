package com.briones.grades.controller;

import com.briones.grades.exception.BadRequestException;
import com.briones.grades.exception.ResourceNotFoundException;
import com.briones.grades.model.Exam;
import com.briones.grades.model.dto.response.UserResponseDto;
import com.briones.grades.service.ExamService;
import com.briones.grades.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClientException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Exam> getAllExams() {
        return examService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Exam getExamById(@PathVariable Long id){
         try {
            return examService.findById(id);
        } catch (ResourceNotFoundException e) {
             throw new ResourceNotFoundException("Exam with ID " + id + " not found");
         }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Exam updateExam(@PathVariable Long id,@Valid @RequestBody Exam exam) {
        Exam existinExam = examService.findById(id);
        if (existinExam == null) throw new ResourceNotFoundException("Exam not found with id: " + id);
        if (exam.getDescription() != null) existinExam.setDescription(exam.getDescription());
        if (exam.getExamDate() != null) existinExam.setExamDate(exam.getExamDate());
        if (exam.getMaxScore() != null) existinExam.setMaxScore(exam.getMaxScore());
        if (exam.getName() != null) existinExam.setName(exam.getName());
        return examService.save(exam);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Exam addExam(@Valid @RequestBody Exam exam, @RequestParam(value = "userId", required = false) UUID userId) {

        if (userId != null) {
            try {
                UserResponseDto user = userService.fetchUserById(userId);
                exam.setCreatedBy(user.email());
            } catch (RestClientException e) {
                throw new BadRequestException("Failed to communicate with the user service.");
            } catch (Exception e) {
                throw new BadRequestException("Unexpected error occurred while retrieving the user.");
            }
        }
        
        return examService.save(exam);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExam(@PathVariable Long id) {
        examService.delete(id);
    }
}
