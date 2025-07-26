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
                Tables.LEDGERS.ID.as("ledgerId"),
                Tables.LEDGERS.DATE.as("date"),
                Tables.LEDGERS.DESCRIPTION.as("description"),
                Tables.LEDGERS.CREATED_AT.as("ledgerCreatedAt"),
                Tables.LEDGERS.UPDATED_AT.as("ledgerUpdatedAt"),
                Tables.LEDGER_ITEMS.TYPE.as("type"),
                Tables.LEDGER_ITEMS.COA.as("coa"),
                Tables.LEDGER_ITEMS.AMOUNT.as("amount"),
                Tables.LEDGER_ITEMS.CREATED_AT.as("ledgerItemCreatedAt"),
                Tables.LEDGER_ITEMS.UPDATED_AT.as("ledgerItemUpdatedAt")
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