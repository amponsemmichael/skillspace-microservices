package com.skillspace.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
class ChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "program_id")
    private Program program;

    private LocalDate changeDate;
    private String changedBy;
    private String changeDescription;

    // Getters and setters
}

