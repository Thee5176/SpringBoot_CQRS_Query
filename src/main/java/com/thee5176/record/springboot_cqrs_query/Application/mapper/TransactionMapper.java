package com.thee5176.record.springboot_cqrs_query.Application.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_query.Application.dto.CreateTransactionDTO;
import com.thee5176.record.springboot_cqrs_query.Domain.model.Transactions;

@Service
public class TransactionMapper {
    final ModelMapper modelMapper;

    TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Transactions map(CreateTransactionDTO createTransactionsDTO) {
        return modelMapper.map(createTransactionsDTO, Transactions.class);
    }
}