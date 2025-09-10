package com.thee5176.ledger_query.report;

import java.util.List;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.record.domain.model.accounting.Tables;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.Element;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@AllArgsConstructor
public class AccountingSettlementRepository {
    private final DSLContext dslContext;
    
    public List<LedgerBalanceSattlementDTO> findByElementId(Element searchElement) {
        // Implementation to fetch LedgerBalanceSattlementDTO by searchElement
        // Join ledgeritem table with coa table to get account balance type
        // Map the result to LedgerBalanceSattlementDTO
        // Return Optional.of(dto) if found, else Optional.empty()
        
        return dslContext.select(Tables.LEDGERS.DATE, Tables.LEDGER_ITEMS.COA, Tables.LEDGER_ITEMS.AMOUNT, Tables.CODE_OF_ACCOUNT.TYPE, Tables.LEDGER_ITEMS.TYPE)
            .from(Tables.LEDGER_ITEMS)
            .join(Tables.LEDGERS)
            .on(Tables.LEDGER_ITEMS.LEDGER_ID.eq(Tables.LEDGERS.ID))

            .join(Tables.CODE_OF_ACCOUNT)
            .on(Tables.LEDGER_ITEMS.COA.eq(Tables.CODE_OF_ACCOUNT.CODE))

            .where(Tables.CODE_OF_ACCOUNT.ELEMENT.eq(Element.lookupLiteral(searchElement.name())))
            .fetchInto(LedgerBalanceSattlementDTO.class);
    }
}
