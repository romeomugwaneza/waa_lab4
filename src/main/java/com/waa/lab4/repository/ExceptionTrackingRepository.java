package com.waa.lab4.repository;

import com.waa.lab4.domain.ExceptionTracking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionTrackingRepository extends JpaRepository<ExceptionTracking, Long> {
}
