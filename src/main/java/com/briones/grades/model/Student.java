package com.briones.grades.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;


/**
 * Represents a student in the system.
 */
@Entity @Table(name = "students")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStudent;
    private UUID userId;

    @Column(unique = true)
    private Long studentCode;

    private String username;
    private String firstName;
    private String lastName;

    @Column(unique = true)
    private String email;

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate birthDate;

    // Relación directa solo con Exámenes
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "student_exams",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "exam_id")
    )
    private List<Exam> exams = new ArrayList<>();

}
