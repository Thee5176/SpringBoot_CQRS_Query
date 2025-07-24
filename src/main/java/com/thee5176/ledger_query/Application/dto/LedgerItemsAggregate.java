package com.thee5176.ledger_query.Application.dto;

import com.thee5176.ledger_query.Domain.model.enums.BalanceType;
import lombok.Data;

@Data
public class LedgerItemsAggregate {
    private Integer coa;
    private Double amount;
    private BalanceType type;
}
