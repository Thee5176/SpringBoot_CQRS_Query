package com.thee5176.ledger_query.Application.dto;

import com.thee5176.ledger_query.Domain.model.enums.BalanceType;

public record LedgerItemsAggregate (
    Integer coa,
    Double amount,
    BalanceType type
){
    
}
