package com.thee5176.ledger_query.Infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import org.jooq.DSLContext;
import org.jooq.SelectSelectStep;
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

    // Reusable customized DSLContext for fetching LedgersQueryOutput DTOs
    private SelectSelectStep<?> fetchDtoContext() {
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
        );
    }
    
    public Optional<Ledgers> getAllLedgers() {
        return dslContext.selectFrom(Tables.LEDGERS)
            .fetchOptionalInto(Ledgers.class);
    }

    public Optional<Ledgers> getLedgerById(@NotNull UUID id) {
        return dslContext.selectFrom(Tables.LEDGERS)
            .where(Tables.LEDGERS.ID.eq(id))
            .fetchOptionalInto(Ledgers.class);
    }

    public Optional<LedgersQueryOutput> getAllLedgersDTO() {
        return fetchDtoContext()
            .from(Tables.LEDGERS)
            .leftJoin(Tables.LEDGER_ITEMS)
            .on(Tables.LEDGERS.ID.eq(Tables.LEDGER_ITEMS.LEDGER_ID))
            .fetchOptionalInto(LedgersQueryOutput.class);
    }

    public Optional<LedgersQueryOutput> getLedgerDTOById(@NotNull UUID id) {
        return fetchDtoContext()
            .from(Tables.LEDGERS)
            .leftJoin(Tables.LEDGER_ITEMS)
            .on(Tables.LEDGERS.ID.eq(Tables.LEDGER_ITEMS.LEDGER_ID))
            .where((Tables.LEDGERS.ID).eq(id))
            .fetchOptionalInto(LedgersQueryOutput.class);
    }
}