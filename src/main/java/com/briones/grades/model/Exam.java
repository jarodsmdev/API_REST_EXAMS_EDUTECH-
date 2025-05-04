package com.briones.grades.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity @Table(name = "exams")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExam;

    private String name;
    private String description;
    private LocalDate examDate;
    private Double maxScore;

    // Relación con Notas
    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Grade> grades = new ArrayList<>();

    // Relación inversa con Estudiantes
    @ManyToMany(mappedBy = "exams")
    private List<Student> students = new ArrayList<>();

    // Método para agregar estudiante al examen
    public void addStudent(Student student) {
        this.students.add(student);
        student.getExams().add(this);
    }
}
