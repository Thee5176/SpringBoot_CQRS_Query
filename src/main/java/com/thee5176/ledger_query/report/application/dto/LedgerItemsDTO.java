package com.thee5176.ledger_query.report.application.dto;

import com.thee5176.ledger_query.record.domain.model.enums.BalanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LedgerItemsDTO{

    Integer coa;
    Double amount;
    BalanceType balanceType;

    public Double getBalance() {
        return BalanceType.Debit.equals(balanceType) ? amount : amount * -1;
    }
}
