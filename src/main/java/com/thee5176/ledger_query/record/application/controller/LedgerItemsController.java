package com.thee5176.ledger_query.record.application.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.record.application.exception.ItemNotFoundException;
import com.thee5176.ledger_query.record.domain.model.accounting.tables.pojos.LedgerItems;
import com.thee5176.ledger_query.record.infrastructure.repository.LedgerItemsRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LedgerItemsController {

    private final LedgerItemsRepository ledgerItemsRepository;

    @GetMapping("/api/ledger-items/all")
    public LedgerItems testRepository() {
        return ledgerItemsRepository.getAllLedgerItems()
            .orElseThrow(() -> new ItemNotFoundException("No ledger items found"));
    }

    @GetMapping("/api/ledger-items")
    public LedgerItems getLedgerItems(@RequestParam(name = "uuid") UUID uuid) {
        return ledgerItemsRepository.getLedgerItemsById(uuid)
            .orElseThrow(() -> new ItemNotFoundException("Ledger item not found"));
    }
}
