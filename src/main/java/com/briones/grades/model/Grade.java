package com.briones.grades.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;

@Entity @Table(name = "grades") @Check(constraints = "grade_value >= 0 AND grade_value <= 7")
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idGrade;

    @Column(name = "grade_value", nullable = false)
    private Double value;

    private String comments;

    @Column(name = "recorded_date", updatable = false)
    private LocalDate recordedDate = LocalDate.now();

    // Relación con Examen
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;

    @Transient
    public boolean isPassed() {
        return value >= 4.0; // Ejemplo de criterio de aprobación
    }
}
