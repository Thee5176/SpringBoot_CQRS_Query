package com.thee5176.ledger_query.Infrastructure.repository;

import java.util.List;
import java.util.UUID;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.Application.dto.LedgersQueryOutput;
import com.thee5176.ledger_query.Domain.model.Tables;
import com.thee5176.ledger_query.Domain.model.tables.pojos.Ledgers;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
@AllArgsConstructor
public class LedgersRepository {
    
    private final DSLContext dslContext;
    
    public List<Ledgers> getAllLedgers() {
        return dslContext.selectFrom(Tables.LEDGERS)
            .fetchInto(Ledgers.class);
    }

    public Ledgers getLedgerById(@NotNull UUID id) {
        return dslContext.selectFrom(Tables.LEDGERS)
            .where(Tables.LEDGERS.ID.eq(id))
            .fetchOneInto(Ledgers.class);
    }

    public List<LedgersQueryOutput> getAllLedgersDTO() {
        return dslContext.select(
                Tables.LEDGERS.ID,
                Tables.LEDGERS.DESCRIPTION,
                Tables.LEDGER_ITEMS.ID,
                Tables.LEDGERS.CREATED_AT,
                Tables.LEDGERS.UPDATED_AT,
                Tables.LEDGER_ITEMS.TYPE,
                Tables.LEDGER_ITEMS.COA,
                Tables.LEDGER_ITEMS.AMOUNT,
                Tables.LEDGER_ITEMS.CREATED_AT,
                Tables.LEDGER_ITEMS.UPDATED_AT
            )
            .from(Tables.LEDGERS)
            .leftJoin(Tables.LEDGER_ITEMS)
            .on(Tables.LEDGERS.ID.eq(Tables.LEDGER_ITEMS.LEDGER_ID))
            .fetchInto(LedgersQueryOutput.class);
    }


    public List<LedgersQueryOutput> getLedgerDTOById(@NotNull UUID id) {
        return dslContext.select(Tables.LEDGERS, Tables.LEDGER_ITEMS)
        .from(Tables.LEDGERS)
        .leftJoin(Tables.LEDGER_ITEMS)
        .on(Tables.LEDGERS.ID.eq(Tables.LEDGER_ITEMS.LEDGER_ID))
        .where((Tables.LEDGERS.ID).eq(id))
        .fetchInto(LedgersQueryOutput.class);
    }
}