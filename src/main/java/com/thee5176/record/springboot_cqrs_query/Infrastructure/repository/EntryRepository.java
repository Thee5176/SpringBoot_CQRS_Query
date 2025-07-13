package com.thee5176.record.springboot_cqrs_query.Infrastructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.aspectj.lang.annotation.SuppressAjWarnings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thee5176.record.springboot_cqrs_query.Domain.model.Entries;

@Repository
@SuppressAjWarnings("null")
public interface EntryRepository extends JpaRepository<Entries, UUID> {
    public Optional<Entries> findById(UUID id);
    
    public void deleteById(UUID id);
}
