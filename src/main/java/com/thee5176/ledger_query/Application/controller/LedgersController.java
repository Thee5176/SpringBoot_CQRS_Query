package com.thee5176.ledger_query.Application.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.Application.dto.GetLedgerResponse;
import com.thee5176.ledger_query.Domain.service.LedgerQueryService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LedgersController {

    private final LedgerQueryService ledgerQueryService;

    @GetMapping("/api/ledgers/all")
    public List<GetLedgerResponse> testRepository() {
        return ledgerQueryService.getAllLedgers();
    }

    @GetMapping("/api/ledgers")
    public GetLedgerResponse getLedgerItems(@RequestParam UUID uuid) {
        return ledgerQueryService.getLedgerById(uuid);
    }
}
