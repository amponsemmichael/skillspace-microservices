package com.skillspace.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Program {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String requirement;
    private String requiredEarnedBadges;
    private String additionalEarnedBadges;
    private LocalDate dateOfCommencement;
    private LocalDate dateOfCompletion;
    private String status;
    private boolean isDraft;
    private String coverImageForProgram;

    @OneToMany(mappedBy = "program", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ChangeLog> changeLogs = new ArrayList<>();

}

