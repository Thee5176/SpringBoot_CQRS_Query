package com.thee5176.ledger_query.record.aspect;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.record.application.exception.ItemNotFoundException;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = LayerSpecificLoggingAspectsTest.TestConfig.class)
class LayerSpecificLoggingAspectsTest {

    @Configuration
    @EnableAspectJAutoProxy
    @Import({ ControllerLoggingAspect.class, ServiceLoggingAspect.class, RepositoryLoggingAspect.class })
    static class TestConfig {
        @Bean
        StubController stubController() {
            return new StubController();
        }

        @Bean
        StubService stubService() {
            return new StubService();
        }

        @Bean
        StubRepository stubRepository() {
            return new StubRepository();
        }
    }

    @RestController
    static class StubController {
        void getLedger() {
            throw new ItemNotFoundException("ledger was not found");
        }

        void throwGeneric() {
            throw new IllegalStateException("generic controller failure");
        }
    }

    @Service
    static class StubService {
        void throwAccessDenied() {
            throw new AccessDeniedException("owner mismatch");
        }

        void throwGeneric() {
            throw new IllegalArgumentException("generic service failure");
        }
    }

    @Repository
    static class StubRepository {
        void throwNotFound() {
            throw new ItemNotFoundException("query failed");
        }

        void throwGeneric() {
            throw new RuntimeException("generic repository failure");
        }
    }

    @org.springframework.beans.factory.annotation.Autowired
    private StubController controller;

    @org.springframework.beans.factory.annotation.Autowired
    private StubService service;

    @org.springframework.beans.factory.annotation.Autowired
    private StubRepository repository;

    private Logger applicationLogger;

    private Logger securityAuditLogger;

    private ListAppender<ILoggingEvent> applicationAppender;

    private ListAppender<ILoggingEvent> securityAuditAppender;

    @BeforeEach
    void setupLogAppenders() {
        applicationLogger = (Logger) LoggerFactory.getLogger("app.application");
        securityAuditLogger = (Logger) LoggerFactory.getLogger("app.security-audit");

        applicationAppender = new ListAppender<>();
        securityAuditAppender = new ListAppender<>();
        applicationAppender.start();
        securityAuditAppender.start();

        applicationLogger.addAppender(applicationAppender);
        securityAuditLogger.addAppender(securityAuditAppender);
    }

    @AfterEach
    void tearDownLogAppenders() {
        applicationLogger.detachAppender(applicationAppender);
        securityAuditLogger.detachAppender(securityAuditAppender);
        applicationAppender.stop();
        securityAuditAppender.stop();
    }

    private List<String> applicationLogs() {
        return applicationAppender.list.stream().map(ILoggingEvent::getFormattedMessage).toList();
    }

    private List<String> securityAuditLogs() {
        return securityAuditAppender.list.stream().map(ILoggingEvent::getFormattedMessage).toList();
    }

    @Test
    void controllerAspect_logsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> controller.getLedger());

        assertThat(applicationLogs()).anyMatch(log ->
                log.contains("Controller query failure method=getLedger message=ledger was not found"));
    }

    @Test
    void serviceAspect_logsAccessDeniedException() {
        assertThrows(AccessDeniedException.class, () -> service.throwAccessDenied());

        assertThat(securityAuditLogs()).anyMatch(log ->
                log.contains("Service authorization failure method=throwAccessDenied message=owner mismatch"));
    }

    @Test
    void repositoryAspect_logsItemNotFoundException() {
        assertThrows(ItemNotFoundException.class, () -> repository.throwNotFound());

        assertThat(applicationLogs()).anyMatch(log ->
                log.contains("Repository operation failure method=throwNotFound")
                        && log.contains("errorId=")
                        && log.contains("message=query failed"));
        assertThat(applicationLogs()).anyMatch(log ->
                log.contains("Repository operation exception method=throwNotFound")
                        && log.contains("errorId=")
                        && log.contains("message=query failed"));
    }

    @Test
    void aspects_doNotLogForDifferentExceptionTypes() {
        assertThrows(IllegalStateException.class, () -> controller.throwGeneric());
        assertThrows(IllegalArgumentException.class, () -> service.throwGeneric());
        assertThrows(RuntimeException.class, () -> repository.throwGeneric());

        assertThat(applicationLogs()).noneMatch(log -> log.contains("Controller query failure method=throwGeneric"));
        assertThat(securityAuditLogs()).noneMatch(log -> log.contains("Service authorization failure method=throwGeneric"));
        assertThat(applicationLogs()).noneMatch(log -> log.contains("Repository operation failure method=throwGeneric"));
        assertThat(applicationLogs()).noneMatch(log -> log.contains("Repository operation exception method=throwGeneric"));
    }
}
