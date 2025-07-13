package com.thee5176.record.springboot_cqrs_query.Domain.RepositoryImp;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.thee5176.record.springboot_cqrs_query.Domain.model.Transactions;
import com.thee5176.record.springboot_cqrs_query.Infrastructure.repository.TransactionRepository;

@Repository
public class TransactionRepositoryImp {
    @Autowired
    TransactionRepository transactionRepository;

    public List<Transactions> getTransaction() {
        return transactionRepository.findAll();
    }

    public void createTransaction(Transactions transaction) {
        transactionRepository.saveAndFlush(transaction);
    }

    public void updateTransaction(UUID uuid, Transactions transaction) {
        transaction.setId(uuid);
        transactionRepository.saveAndFlush(transaction);
    }

    public void deleteTransaction(UUID uuid) {
        transactionRepository.deleteById(uuid);
    }
}
