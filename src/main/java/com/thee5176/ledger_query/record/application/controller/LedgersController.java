package com.thee5176.ledger_query.record.application.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.record.application.dto.GetLedgerResponse;
import com.thee5176.ledger_query.record.domain.service.LedgersQueryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class LedgersController {

    private final LedgersQueryService ledgersQueryService;

    @GetMapping("/api/ledgers/all")
    public List<GetLedgerResponse> getAllLedgers(@AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        log.info("Request to get all ledgers for user: {}", userId);
        return ledgersQueryService.getAllLedgers(userId);
    }

    @GetMapping("/api/ledgers")
    public GetLedgerResponse getLedger(@AuthenticationPrincipal Jwt jwt, @RequestParam(name = "uuid") UUID uuid) {
        String userId = jwt.getSubject();
        return ledgersQueryService.getLedgerById(uuid, userId);
    }
}