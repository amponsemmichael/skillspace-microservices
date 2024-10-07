package com.skillspace.service;

import com.skillspace.model.ChangeLog;
import com.skillspace.model.Program;
import com.skillspace.repository.ChangeLogRepository;
import com.skillspace.repository.ProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private ChangeLogRepository changeLogRepository;

    public Program createProgram(Program program) {
        program.setDraft(true);
        return programRepository.save(program);
    }

    public List<Program> getAllPrograms() {
        return programRepository.findByIsDraft(false);
    }

    public List<Program> getAllDrafts() {
        return programRepository.findByIsDraft(true);
    }

    public Optional<Program> getProgramById(Long id) {
        return programRepository.findById(id);
    }

    @Transactional
    public Optional<Program> updateProgram(Long id, Program programDetails) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            program.setName(programDetails.getName());
            program.setDescription(programDetails.getDescription());
            program.setRequirement(programDetails.getRequirement());
            program.setRequiredEarnedBadges(programDetails.getRequiredEarnedBadges());
            program.setAdditionalEarnedBadges(programDetails.getAdditionalEarnedBadges());
            program.setDateOfCommencement(programDetails.getDateOfCommencement());
            program.setDateOfCompletion(programDetails.getDateOfCompletion());
            program.setStatus(programDetails.getStatus());
            program.setCoverImageForProgram(programDetails.getCoverImageForProgram());
            Program updatedProgram = programRepository.save(program);

            if (!program.isDraft()) {
                ChangeLog changeLog = new ChangeLog();
                changeLog.setProgram(program);
                changeLog.setChangeDate(LocalDate.now());
                changeLog.setChangedBy("Admin"); // This should be replaced with actual user info
                changeLog.setChangeDescription("Program updated");
                changeLogRepository.save(changeLog);
            }

            return Optional.of(updatedProgram);
        }
        return Optional.empty();
    }

    public boolean deleteProgram(Long id) {
        if (programRepository.existsById(id)) {
            programRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Optional<Program> publishProgram(Long id) {
        Optional<Program> optionalProgram = programRepository.findById(id);
        if (optionalProgram.isPresent()) {
            Program program = optionalProgram.get();
            program.setDraft(false);
            Program publishedProgram = programRepository.save(program);

            ChangeLog changeLog = new ChangeLog();
            changeLog.setProgram(program);
            changeLog.setChangeDate(LocalDate.now());
            changeLog.setChangedBy("Admin"); // This should be replaced with actual user info
            changeLog.setChangeDescription("Program published");
            changeLogRepository.save(changeLog);

            return Optional.of(publishedProgram);
        }
        return Optional.empty();
    }
}

