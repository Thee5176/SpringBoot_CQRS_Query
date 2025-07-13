package com.thee5176.record.springboot_cqrs_query.Domain.RepositoryImp;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.thee5176.record.springboot_cqrs_query.Domain.model.Entries;
import com.thee5176.record.springboot_cqrs_query.Infrastructure.repository.EntryRepository;

@Repository
public class EntryRepositoryImp{
    @Autowired
    EntryRepository entryRepository;

    public List<Entries> getEntry() {
        return entryRepository.findAll();
    }

    public void createEntry(Entries entry) {
        entryRepository.saveAndFlush(entry);
    }

    public void updateEntry(UUID uuid, Entries entry) {
        entry.setId(uuid);
        entryRepository.save(entry);
    }

    public void deleteEntry(UUID uuid) {
        entryRepository.deleteById(uuid);
    }

}