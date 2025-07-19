package com.thee5176.ledger_query.Application.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.Domain.model.tables.pojos.LedgerItems;
import com.thee5176.ledger_query.Infrastructure.repository.LedgerItemsRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LedgerItemsController {

    private final LedgerItemsRepository ledgerItemsRepository;

    @GetMapping("/api/ledger-items/all")
    public List<LedgerItems> testRepository() {
        return ledgerItemsRepository.getAllLedgerItems();
    }

    @GetMapping("/api/ledger-items")
    public LedgerItems getLedgerItems(@RequestParam UUID uuid) {
        return ledgerItemsRepository.getLedgerItemsById(uuid);
    }
}
