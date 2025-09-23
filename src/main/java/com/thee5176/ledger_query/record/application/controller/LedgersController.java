package com.thee5176.ledger_query.record.application.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.record.application.dto.GetLedgerResponse;
import com.thee5176.ledger_query.record.domain.service.LedgersQueryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LedgersController {

    private final LedgersQueryService ledgersQueryService;

    @GetMapping("/api/ledgers/all")
    public List<GetLedgerResponse> getAllLedgers(@AuthenticationPrincipal UserDetails userDetails) {
        return ledgersQueryService.getAllLedgers(userDetails.getUsername());
    }

    @GetMapping("/api/ledgers")
    public GetLedgerResponse getLedger(@RequestParam(name = "uuid") UUID uuid, @AuthenticationPrincipal UserDetails userDetails) {
        return ledgersQueryService.getLedgerById(uuid, userDetails.getUsername());
    }
}