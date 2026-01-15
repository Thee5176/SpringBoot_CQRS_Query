package com.thee5176.ledger_query.report.application.dto;

import java.util.Date;
import com.thee5176.ledger_query.record.domain.model.enums.BalanceType;

public record BaseSattlementDTO(
    Date date, 
    Integer coa,
    Double balance,
    BalanceType accountBalanceType,
    BalanceType ledgerItemBalanceType
) {

}
