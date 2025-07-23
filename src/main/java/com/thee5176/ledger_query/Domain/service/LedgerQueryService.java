package com.thee5176.ledger_query.Domain.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.thee5176.ledger_query.Infrastructure.repository.LedgerItemsRepository;
import com.thee5176.ledger_query.Infrastructure.repository.LedgersRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LedgerQueryService {
    private final LedgersRepository ledgersRepository;
    private final LedgerItemsRepository ledgerItemsRepository;

    public ResponseEntity<String> getAllLedgers() {

    }
}
