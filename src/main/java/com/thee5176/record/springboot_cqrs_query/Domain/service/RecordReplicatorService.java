package com.thee5176.record.springboot_cqrs_query.Domain.service;

import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_query.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_query.Application.dto.CreateTransactionDTO;
import com.thee5176.record.springboot_cqrs_query.Domain.RepositoryImp.EntryRepositoryImp;
import com.thee5176.record.springboot_cqrs_query.Domain.RepositoryImp.TransactionRepositoryImp;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecordReplicatorService {
    private final TransactionRepositoryImp transactionRepositoryImp;

    private final EntryRepositoryImp entryRepositoryImp;

    @Transactional
    public void replicateRecord(CreateRecordDTO createRecordDTO) {
        //Create Transaction
        CreateTransactionDTO transactionDto = createRecordDTO.transaction();
         
        //debug
        System.out.println(transactions);
        
        transactions.setCreatedAt(createRecordDTO.timestamp());
        transactions.setUpdatedAt(createRecordDTO.timestamp());
        transactionRepositoryImp.createTransaction(transactions);

        //Create Entries
        createRecordDTO.entries().stream()
            .map(entryMapper::map)
            .map( entry -> entry.setCreatedAt(createRecordDTO.timestamp()))
            .map( entry -> entry.setUpdatedAt(createRecordDTO.timestamp()))
            .forEach(entryRepositoryImp::createEntry);

        //TODO: Does replication service need validation? -> Validation logic should live in Controller
    }
}
