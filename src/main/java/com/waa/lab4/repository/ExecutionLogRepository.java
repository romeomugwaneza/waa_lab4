package com.waa.lab4.repository;

import com.waa.lab4.domain.ExecutionLogger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExecutionLogRepository extends JpaRepository<ExecutionLogger, Long> {
}
