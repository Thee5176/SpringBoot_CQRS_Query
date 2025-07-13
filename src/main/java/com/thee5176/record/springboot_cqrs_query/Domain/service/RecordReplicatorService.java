package com.thee5176.record.springboot_cqrs_query.Domain.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_query.Application.dto.CreateEntryDTO;
import com.thee5176.record.springboot_cqrs_query.Application.dto.CreateTransactionDTO;
import com.thee5176.record.springboot_cqrs_query.Application.mapper.EntryMapper;
import com.thee5176.record.springboot_cqrs_query.Application.mapper.TransactionMapper;
import com.thee5176.record.springboot_cqrs_query.Domain.RepositoryImp.EntryRepositoryImp;
import com.thee5176.record.springboot_cqrs_query.Domain.RepositoryImp.TransactionRepositoryImp;
import com.thee5176.record.springboot_cqrs_query.Domain.model.Transactions;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecordReplicatorService {
    private final TransactionRepositoryImp transactionRepositoryImp;

    private final EntryRepositoryImp entryRepositoryImp;

    private final TransactionMapper transactionMapper;

    private final EntryMapper entryMapper;

    @Transactional
    public void replicateRecord(CreateTransactionDTO createTransactionsDTO, List<CreateEntryDTO> listOfCreateEntryDTO) {
        Transactions transactions = transactionMapper.map(createTransactionsDTO);
        transactionRepositoryImp.createTransaction(transactions);

        listOfCreateEntryDTO.stream()
            .map(entryMapper::map)
            .forEach(entryRepositoryImp::createEntry);
    }
}
