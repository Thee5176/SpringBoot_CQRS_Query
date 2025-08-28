package com.thee5176.ledger_query.Application.dto;

import java.util.Date;
import com.thee5176.ledger_query.Domain.model.enums.BalanceType;

public record LedgerBalanceSattlementDTO(
    Date date, 
    Integer coa,
    Double balance,
    BalanceType accountBalanceType,
    BalanceType ledgerItemBalanceType
) {

}
