package com.thee5176.record.springboot_cqrs_query.Infrastructure.repository;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.thee5176.record.springboot_cqrs_query.Domain.model.LedgerItems;

@Repository
@SuppressWarnings("null")
public interface LedgerItemsRepository extends JpaRepository<LedgerItems, UUID> {
    public Optional<LedgerItems> findById(UUID id);

    public void deleteById(UUID id);
}
