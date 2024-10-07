package com.skillspace.controller;

import com.skillspace.model.Program;
import com.skillspace.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/programs")
class ProgramController {
    @Autowired
    private ProgramService programService;

    @PostMapping("/create")
    public ResponseEntity<Program> createProgram(@RequestBody Program program) {
        Program savedProgram = programService.createProgram(program);
        return ResponseEntity.ok(savedProgram);
    }

    @GetMapping
    public List<Program> getAllPrograms() {
        return programService.getAllPrograms();
    }

    @GetMapping("/drafts")
    public List<Program> getAllDrafts() {
        return programService.getAllDrafts();
    }

    @GetMapping("/{programId}")
    public ResponseEntity<Program> getProgramById(@PathVariable Long programId) {
        return programService.getProgramById(programId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{programId}")
    public ResponseEntity<Program> updateProgram(@PathVariable Long programId, @RequestBody Program programDetails) {
        return programService.updateProgram(programId, programDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{programId}")
    public ResponseEntity<Void> deleteProgram(@PathVariable Long programId) {
        if (programService.deleteProgram(programId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{programId}/publish")
    public ResponseEntity<Program> publishProgram(@PathVariable Long programId) {
        return programService.publishProgram(programId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
