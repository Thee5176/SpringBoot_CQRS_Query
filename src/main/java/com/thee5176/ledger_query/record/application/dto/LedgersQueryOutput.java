package com.thee5176.ledger_query.record.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.BalanceType;
import lombok.Data;

@Data
public class LedgersQueryOutput {
    private UUID ledgerId;
    private LocalDate date;
    private String description;
    private LocalDateTime ledgerCreatedAt;
    private LocalDateTime ledgerUpdatedAt;
    private BalanceType type;
    private Integer coa;
    private Double amount;
    private LocalDateTime ledgerItemCreatedAt;
    private LocalDateTime ledgerItemUpdatedAt;
}
