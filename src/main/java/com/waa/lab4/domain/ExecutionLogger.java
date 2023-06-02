package com.waa.lab4.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "execution_logs")
public class ExecutionLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionId;
    private LocalDateTime logDate;
    private long executionTime;
    private long principle; // userId

    private String operation;
}
