package com.thee5176.record.springboot_cqrs_query.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thee5176.record.springboot_cqrs_query.model.Transactions;


@Repository
@SuppressWarnings("null")
public interface TransactionRepository extends JpaRepository<Transactions, UUID> {
    public Optional<Transactions> findById(UUID id);
    public void deleteById(UUID id);
}
