package com.thee5176.ledger_query.Infrastructure.repository;

import java.util.List;
import java.util.UUID;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.Domain.model.Tables;
import com.thee5176.ledger_query.Domain.model.tables.pojos.LedgerItems;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class LedgerItemsRepository {
    
    private final DSLContext dslContext;

    public List<LedgerItems> getAllLedgerItems() {
        return dslContext.selectFrom(Tables.LEDGER_ITEMS)
                .fetchInto(LedgerItems.class);
    }

    public LedgerItems getLedgerItemsById(UUID id) {
        return dslContext.selectFrom(Tables.LEDGER_ITEMS)
            .where(Tables.LEDGER_ITEMS.ID.eq(id))
            .fetchOneInto(LedgerItems.class);
    }
}