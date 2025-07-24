package com.thee5176.ledger_query.Application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record GetLedgerResponse(
    UUID ledger_id,
    LocalDate date,
    String description,
    List<LedgerItemsAggregate> ledgerItems,
    LocalDateTime created_at,
    LocalDateTime updated_at
) {

}
