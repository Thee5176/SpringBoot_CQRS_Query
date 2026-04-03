package com.thee5176.ledger_query.record.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ServiceLoggingAspect {

    private static final Logger MESSAGE_LOG = LoggerFactory.getLogger("app.security-audit");

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void serviceLayer() {
    }

    @AfterThrowing(pointcut = "serviceLayer()", throwing = "ex")
    public void logAccessDeniedException(JoinPoint joinPoint, AccessDeniedException ex) {
        MESSAGE_LOG.warn("Service authorization failure method={} message={}",
                joinPoint.getSignature().getName(), ex.getMessage());
    }
}
