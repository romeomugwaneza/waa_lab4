package com.waa.lab4.aop;

import com.waa.lab4.domain.ExceptionTracking;
import com.waa.lab4.repository.ExceptionTrackingRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Aspect
@Component
public class ExceptionAspect {

    final
    ExceptionTrackingRepository exceptionTrackingRepository;
    private static final Logger logger = LoggerFactory.getLogger(ExceptionAspect.class);

    public ExceptionAspect(ExceptionTrackingRepository exceptionTrackingRepository) {
        this.exceptionTrackingRepository = exceptionTrackingRepository;
    }

    @AfterThrowing(value = "@annotation(LogException)", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {

        String methodName = joinPoint.getSignature().toShortString();

        ExceptionTracking exceptionTracking = new ExceptionTracking();

        exceptionTracking.setExceptionType(exception.getClass().getSimpleName());
        exceptionTracking.setLogDate(LocalDateTime.now());
        exceptionTracking.setOperation(joinPoint.toLongString());
        exceptionTracking.setPrinciple(1L);

        exceptionTrackingRepository.save(exceptionTracking);

        logger.error("Exception occurred in {}: {}", methodName, exception.getMessage());
    }
}
