package com.thee5176.ledger_query.Application.controller;

import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.Application.exception.ItemNotFoundException;
import com.thee5176.ledger_query.Domain.model.tables.pojos.LedgerItems;
import com.thee5176.ledger_query.Infrastructure.repository.LedgerItemsRepository;
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
