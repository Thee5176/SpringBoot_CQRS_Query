package com.thee5176.ledger_query.Application.controller;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.Domain.model.tables.pojos.Ledgers;
import com.thee5176.ledger_query.Infrastructure.repository.LedgersRepository;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class LedgersController {

    private final LedgersRepository ledgersRepository;

    @GetMapping("/api/ledgers/all")
    public List<Ledgers> testRepository() {
        return ledgersRepository.getAllLedgers();
    }

    @GetMapping("/api/ledgers")
    public Ledgers getLedgerItems(@RequestParam UUID uuid) {
        return ledgersRepository.getLedgerById(uuid);
    }
}
