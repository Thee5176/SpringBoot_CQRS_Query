package com.thee5176.ledger_query.record.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class GetLedgerResponse {
    private UUID ledgerId;
    private LocalDate date;
    private String description;
    private List<LedgerItemsAggregate> ledgerItems;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
