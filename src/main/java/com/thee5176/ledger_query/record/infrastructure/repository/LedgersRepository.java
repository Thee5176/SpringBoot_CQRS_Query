package com.thee5176.ledger_query.record.infrastructure.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.jooq.DSLContext;
import org.jooq.SelectSelectStep;
import org.springframework.stereotype.Repository;
import com.thee5176.ledger_query.record.application.dto.LedgersQueryOutput;
import com.thee5176.ledger_query.record.application.exception.ItemNotFoundException;
import com.thee5176.ledger_query.record.domain.model.accounting.Tables;
import com.thee5176.ledger_query.record.domain.model.accounting.tables.pojos.Ledgers;
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
    
    public List<Ledgers> getAllLedgers() {
        try {
            return dslContext.selectFrom(Tables.LEDGERS)
                .fetchInto(Ledgers.class);
        } catch (Exception e) {
            log.error("Error fetching all ledgers", e);
            throw new ItemNotFoundException("Error fetching all ledgers");
        }
    }

    public Optional<Ledgers> getLedgerById(@NotNull UUID id) {
        return dslContext.selectFrom(Tables.LEDGERS)
            .where(Tables.LEDGERS.ID.eq(id))
            .fetchOptionalInto(Ledgers.class);
    }

    public List<LedgersQueryOutput> getAllLedgers(@NotNull Long userId) {
        try {
            log.info("Fetching ledgers for user ID: {}", userId);
            
            List<LedgersQueryOutput> results = fetchDtoContext()
                .from(Tables.LEDGERS)
                .leftJoin(Tables.LEDGER_ITEMS)
                .on(Tables.LEDGERS.ID.eq(Tables.LEDGER_ITEMS.LEDGER_ID))
                .where(Tables.LEDGERS.OWNER_ID.eq(userId))
                .fetchInto(LedgersQueryOutput.class);
                
            log.info("Found {} ledger query outputs for user ID: {}", results.size(), userId);
            
            // Log some sample data for debugging
            if (!results.isEmpty()) {
                LedgersQueryOutput first = results.get(0);
                log.info("Sample ledger: ID={}, Description={}, Date={}", 
                    first.getLedgerId(), first.getDescription(), first.getDate());
            }
            
            return results;
        } catch (Exception e) {
            log.error("Error fetching all ledgers DTOs for user ID: {}", userId, e);
            throw new ItemNotFoundException("Error fetching all ledgers DTOs for user: " + userId);
        }
    }

    public Optional<LedgersQueryOutput> getLedgerById(@NotNull UUID id, @NotNull Long userId) {
            return fetchDtoContext()
                .from(Tables.LEDGERS)
                .leftJoin(Tables.LEDGER_ITEMS)
                .on(Tables.LEDGERS.ID.eq(Tables.LEDGER_ITEMS.LEDGER_ID))
                .where((Tables.LEDGERS.ID).eq(id).and(Tables.LEDGERS.OWNER_ID.eq(userId)))
                .fetchOptionalInto(LedgersQueryOutput.class);
    }
}