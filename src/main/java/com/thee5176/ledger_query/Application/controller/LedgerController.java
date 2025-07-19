package com.thee5176.ledger_query.Application.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.Domain.model.tables.pojos.LedgerItems;
import com.thee5176.ledger_query.Domain.model.tables.pojos.Ledgers;
import com.thee5176.ledger_query.Infrastructure.repository.LedgerItemsRepository;
import com.thee5176.ledger_query.Infrastructure.repository.LedgerRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LedgerController {

    private final LedgerRepository ledgerRepository;
    private final LedgerItemsRepository ledgerItemsRepository;

    @GetMapping("/api/ledgers/all")
    public List<Ledgers> testRepository() {
        return ledgerRepository.getAllLedgers();
    }

    @GetMapping("/api/ledger-items")
    public List<LedgerItems> getLedgerItems() {
        return ledgerItemsRepository.getAllLedgerItems();
    }
}
