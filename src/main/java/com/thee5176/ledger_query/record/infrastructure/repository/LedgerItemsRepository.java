package com.thee5176.ledger_query.record.infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.record.domain.model.Tables;
import com.thee5176.ledger_query.record.domain.model.tables.pojos.LedgerItems;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class LedgerItemsRepository {
    
    private final DSLContext dslContext;

    public Optional<LedgerItems> getAllLedgerItems() {
        return dslContext.selectFrom(Tables.LEDGER_ITEMS)
                .fetchOptionalInto(LedgerItems.class);
    }

    public Optional<LedgerItems> getLedgerItemsById(UUID id) {
        return dslContext.selectFrom(Tables.LEDGER_ITEMS)
            .where(Tables.LEDGER_ITEMS.ID.eq(id))
            .fetchOptionalInto(LedgerItems.class);
    }
}