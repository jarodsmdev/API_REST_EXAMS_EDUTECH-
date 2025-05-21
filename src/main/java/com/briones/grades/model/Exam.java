package com.briones.grades.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.format.annotation.DateTimeFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "exams")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idExam;

    @NotNull
    private String name;
    private String description;

    //@DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate examDate;

    @PositiveOrZero
    private Double maxScore;

    @Email
    @Column(name = "created_by", nullable = true)
    private String createdBy;
}
