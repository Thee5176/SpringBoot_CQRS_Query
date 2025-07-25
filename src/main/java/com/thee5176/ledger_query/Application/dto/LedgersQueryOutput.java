package com.thee5176.ledger_query.Application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import com.thee5176.ledger_query.Domain.model.enums.BalanceType;
import lombok.Data;

@Data
public class LedgersQueryOutput {
    private UUID ledgerId;
    private LocalDate date;
    private String description;
    private Integer coa;
    private Double amount;
    private BalanceType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
