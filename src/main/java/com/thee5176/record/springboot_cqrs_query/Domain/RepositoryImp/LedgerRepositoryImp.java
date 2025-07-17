package com.thee5176.record.springboot_cqrs_query.Domain.RepositoryImp;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.thee5176.record.springboot_cqrs_query.Domain.model.Ledgers;
import com.thee5176.record.springboot_cqrs_query.Infrastructure.repository.LedgerRepository;

@Repository
public class LedgerRepositoryImp {
    @Autowired
    LedgerRepository ledgerRepository;

    public List<Ledgers> getLedger() {
        return ledgerRepository.findAll();
    }

    public void createLedger(Ledgers ledger) {
        ledgerRepository.saveAndFlush(ledger);
    }

    public void updateLedger(UUID uuid, Ledgers ledger) {
        ledger.setId(uuid);
        ledgerRepository.saveAndFlush(ledger);
    }

    public void deleteLedger(UUID uuid) {
        ledgerRepository.deleteById(uuid);
    }
}
