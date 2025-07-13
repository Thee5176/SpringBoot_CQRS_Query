package com.thee5176.record.springboot_cqrs_query.Application.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_query.Application.dto.CreateTransactionDTO;
import com.thee5176.record.springboot_cqrs_query.Domain.model.Transactions;
import jakarta.annotation.PostConstruct;

@Service
public class TransactionMapper {
    private final ModelMapper modelMapper;

    public TransactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public Transactions map(CreateTransactionDTO createTransactionDTO) {
        return modelMapper.map(createTransactionDTO, Transactions.class);
    }
}