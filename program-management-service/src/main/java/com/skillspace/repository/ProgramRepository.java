package com.skillspace.repository;

import com.skillspace.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface ProgramRepository extends JpaRepository<Program, Long> {
    List<Program> findByIsDraft(boolean isDraft);
}
