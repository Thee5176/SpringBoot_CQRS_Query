package com.thee5176.ledger_query.report;

import java.util.Date;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.BalanceType;

public record LedgerBalanceSattlementDTO(
    Date date, 
    Integer coa,
    Double balance,
    BalanceType accountBalanceType,
    BalanceType ledgerItemBalanceType
) {

}
