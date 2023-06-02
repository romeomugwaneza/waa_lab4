package com.waa.lab4.aop;

import com.waa.lab4.domain.ExecutionLogger;
import com.waa.lab4.repository.ExecutionLogRepository;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class ExecutionTimeAspect {
    private static final Logger logger = LoggerFactory.getLogger(ExecutionTimeAspect.class);

    final
    ExecutionLogRepository executionLogRepository;

    public ExecutionTimeAspect(ExecutionLogRepository executionLogRepository) {
        this.executionLogRepository = executionLogRepository;
    }

    @Around("@annotation(ExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        String operation = joinPoint.getSignature().toLongString();
        ExecutionLogger executionLogger = new ExecutionLogger();
        executionLogger.setOperation(operation);
        executionLogger.setLogDate(LocalDateTime.now());
        executionLogger.setExecutionTime(executionTime);
        executionLogger.setPrinciple(1L);

        executionLogRepository.save(executionLogger);

        logger.info("*** {} executed in {} ms ***", joinPoint.getSignature(), executionTime);

        return result;
    }
}

