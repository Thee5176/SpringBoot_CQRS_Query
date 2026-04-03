package com.thee5176.ledger_query.record.aspect;

import java.util.UUID;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import com.thee5176.ledger_query.record.application.exception.ItemNotFoundException;

@Component
@Aspect
public class RepositoryLoggingAspect {

    private static final Logger MESSAGE_LOG = LoggerFactory.getLogger("app.application");

    @Pointcut("within(@org.springframework.stereotype.Repository *)")
    public void repositoryLayer() {
    }

    @AfterThrowing(pointcut = "repositoryLayer()", throwing = "ex")
    public void logRepositoryException(JoinPoint joinPoint, ItemNotFoundException ex) {
        String errorId = UUID.randomUUID().toString();
        MDC.put("error_id", errorId);
        try {
            MESSAGE_LOG.error("Repository operation failure method={} errorId={} message={}",
                    joinPoint.getSignature().getName(), errorId, ex.getMessage());
            MESSAGE_LOG.error("Repository operation exception method={} errorId={} message={}",
                    joinPoint.getSignature().getName(), errorId, ex.getMessage(), ex);
        } finally {
            MDC.remove("error_id");
        }
    }
}
