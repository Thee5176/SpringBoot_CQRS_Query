package com.thee5176.ledger_query.Application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import com.thee5176.ledger_query.Domain.model.enums.BalanceType;

public record LedgersQueryOutput(
    UUID ledger_id,
    LocalDate date,
    String description,
    Integer coa,
    Double amount,
    BalanceType type,
    LocalDateTime created_at,
    LocalDateTime updated_at
) {
    
}