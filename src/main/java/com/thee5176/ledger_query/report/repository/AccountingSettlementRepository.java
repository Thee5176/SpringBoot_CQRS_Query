package com.thee5176.ledger_query.report.repository;

import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.record.domain.model.accounting.Tables;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.Element;
import com.thee5176.ledger_query.report.application.dto.BaseSattlementDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@AllArgsConstructor
public class AccountingSettlementRepository {
    private final DSLContext dslContext;
    
    // Implementation to fetch BaseSattlementDTO by searchElement
    public List<BaseSattlementDTO> findByElementId(Element searchElement) {
        return dslContext
            .select(
                Tables.LEDGERS.DATE.as("date"),
                Tables.LEDGER_ITEMS.COA.as("coa"),
                Tables.LEDGER_ITEMS.AMOUNT.cast(Double.class).as("balance"),
                Tables.CODE_OF_ACCOUNT.TYPE.as("accountBalanceType"),
                Tables.LEDGER_ITEMS.TYPE.as("ledgerItemBalanceType")
            )
            .from(Tables.LEDGER_ITEMS)
            .join(Tables.LEDGERS)
            .on(Tables.LEDGER_ITEMS.LEDGER_ID.eq(Tables.LEDGERS.ID))
        
        // Join ledgeritem table with coa table to get account balance type
            .join(Tables.CODE_OF_ACCOUNT)
            .on(Tables.LEDGER_ITEMS.COA.eq(Tables.CODE_OF_ACCOUNT.CODE))
        
        // Map the result to BaseSattlementDTO
            .where(Tables.CODE_OF_ACCOUNT.ELEMENT.eq(Element.lookupLiteral(searchElement.name())))
            .fetchInto(BaseSattlementDTO.class);
    }
}
