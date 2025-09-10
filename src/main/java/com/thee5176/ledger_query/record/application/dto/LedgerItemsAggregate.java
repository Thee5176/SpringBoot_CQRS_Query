package com.thee5176.ledger_query.record.application.dto;

import java.time.LocalDateTime;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.BalanceType;
import lombok.Data;

@Data
public class LedgerItemsAggregate {
    private Integer coa;
    private Double amount;
    private BalanceType type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}