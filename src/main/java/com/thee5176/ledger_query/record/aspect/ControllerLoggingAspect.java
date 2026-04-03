package com.thee5176.ledger_query.record.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import com.thee5176.ledger_query.record.application.exception.ItemNotFoundException;

@Component
@Aspect
public class ControllerLoggingAspect {

    private static final Logger MESSAGE_LOG = LoggerFactory.getLogger("app.application");

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void controllerLayer() {
    }

    @Pointcut("controllerLayer() && execution(* *(..))")
    public void controllerEndpoints() {
    }

    @AfterReturning(pointcut = "controllerEndpoints()")
    public void logControllerSuccess(JoinPoint joinPoint) {
        String userId = extractUserId(joinPoint.getArgs());
        MESSAGE_LOG.info("Controller success method={} userId={}", joinPoint.getSignature().getName(), userId);
    }

    @AfterThrowing(pointcut = "controllerEndpoints()", throwing = "ex")
    public void logNotFoundException(JoinPoint joinPoint, ItemNotFoundException ex) {
        MESSAGE_LOG.warn("Controller query failure method={} message={}",
                joinPoint.getSignature().getName(), ex.getMessage());
    }

    private String extractUserId(Object[] args) {
        for (Object arg : args) {
            if (arg instanceof Jwt jwt) {
                return jwt.getSubject();
            }
        }
        return "unknown-user";
    }
}
