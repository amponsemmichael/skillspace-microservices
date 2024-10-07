package com.skillspace.repository;

import com.skillspace.model.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

interface ChangeLogRepository extends JpaRepository<ChangeLog, Long> {}
