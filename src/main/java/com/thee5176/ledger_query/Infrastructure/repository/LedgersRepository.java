package com.thee5176.ledger_query.Infrastructure.repository;

import java.util.List;
import java.util.UUID;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.Application.dto.LedgersOutputDTO;
import com.thee5176.ledger_query.Domain.model.Tables;
import com.thee5176.ledger_query.Domain.model.tables.pojos.Ledgers;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class LedgersRepository {
    
    private final DSLContext dslContext;
    
    public List<Ledgers> getAllLedgers() {
        return dslContext.selectFrom(Tables.LEDGERS)
            .fetchInto(Ledgers.class);
    }

    public Ledgers getLedgerById(UUID id) {
        return dslContext.selectFrom(Tables.LEDGERS)
            .where(Tables.LEDGERS.ID.eq(id))
            .fetchOneInto(Ledgers.class);
    }

    public List<LedgersOutputDTO> getAllLedgersOutputDTO() {
        return dslContext.select(Tables.LEDGERS, Tables.LEDGER_ITEMS)
            .from(Tables.LEDGERS)
            .leftJoin(Tables.LEDGER_ITEMS)
            .on(Tables.LEDGERS.ID.eq(Tables.LEDGER_ITEMS.LEDGER_ID))
            .fetchInto(LedgersOutputDTO.class);
    }
}