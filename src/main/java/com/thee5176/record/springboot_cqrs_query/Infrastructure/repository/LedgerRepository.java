package com.thee5176.record.springboot_cqrs_query.Infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.thee5176.record.springboot_cqrs_query.Domain.model.Ledgers;


@Repository
@SuppressWarnings("null")
public interface LedgerRepository extends JpaRepository<Ledgers, UUID> {
    public Optional<Ledgers> findById(UUID id);
    public void deleteById(UUID id);
}
