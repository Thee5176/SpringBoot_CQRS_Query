package com.thee5176.record.springboot_cqrs_query.Domain.RepositoryImp;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.thee5176.record.springboot_cqrs_query.Domain.model.LedgerItems;
import com.thee5176.record.springboot_cqrs_query.Infrastructure.repository.LedgerItemsMapper;

@Repository
public class LedgerItemsRepositoryImp{
    @Autowired
    LedgerItemsMapper LedgerItemsMapper;

    public List<LedgerItems> getledgerItems() {
        return LedgerItemsMapper.findAll();
    }

    public void createLedgerItems(LedgerItems ledgerItems) {
        LedgerItemsMapper.saveAndFlush(ledgerItems);
    }

    public void updateledgerItems(UUID uuid, LedgerItems ledgerItems) {
        ledgerItems.setId(uuid);
        LedgerItemsMapper.save(ledgerItems);
    }

    public void deleteledgerItems(UUID uuid) {
        LedgerItemsMapper.deleteById(uuid);
    }

}