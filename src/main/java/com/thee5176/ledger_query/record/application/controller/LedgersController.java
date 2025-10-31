package com.thee5176.ledger_query.record.application.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.record.application.dto.GetLedgerResponse;
import com.thee5176.ledger_query.record.domain.model.accounting.tables.pojos.Ledgers;
import com.thee5176.ledger_query.record.domain.service.LedgersQueryService;
import com.thee5176.ledger_query.record.infrastructure.repository.LedgersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class LedgersController {

    private final LedgersQueryService ledgersQueryService;
    private final LedgersRepository ledgersRepository;

    @GetMapping("/api/ledgers/all")
    public List<GetLedgerResponse> getAllLedgers(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("Request to get all ledgers for user: {}", userDetails.getUsername());
        return ledgersQueryService.getAllLedgers(userDetails.getUsername());
    }

    @GetMapping("/api/ledgers")
    public GetLedgerResponse getLedger(@AuthenticationPrincipal UserDetails userDetails, @RequestParam(name = "uuid") UUID uuid) {
        return ledgersQueryService.getLedgerById(uuid, userDetails.getUsername());
    }

    // Debug endpoint to see all ledgers without filtering (remove in production)
    @GetMapping("/api/ledgers/debug/all")
    public List<Ledgers> getAllLedgersDebug(@AuthenticationPrincipal UserDetails userDetails) {
        log.info("DEBUG: Fetching all ledgers without filtering for user: {}", userDetails.getUsername());
        List<Ledgers> allLedgers = ledgersRepository.getAllLedgers();
        log.info("DEBUG: Found {} total ledgers in database", allLedgers.size());
        
        // Log owner IDs for debugging
        allLedgers.forEach(ledger -> 
            log.info("DEBUG: Ledger ID={}, Owner ID={}, Description={}", 
                ledger.getId(), ledger.getOwnerId(), ledger.getDescription())
        );
        
        return allLedgers;
    }
}