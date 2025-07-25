package com.thee5176.ledger_query.Application.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.thee5176.ledger_query.Application.dto.GetLedgerResponse;
import com.thee5176.ledger_query.Application.dto.LedgerItemsAggregate;
import com.thee5176.ledger_query.Application.dto.LedgersQueryOutput;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        // Configure mapping from LedgersQueryOutput to GetLedgerResponse
        modelMapper.createTypeMap(LedgersQueryOutput.class, GetLedgerResponse.class)
            .addMapping(LedgersQueryOutput::getLedgerId, GetLedgerResponse::setLedgerId)
            .addMapping(LedgersQueryOutput::getDate, GetLedgerResponse::setDate)
            .addMapping(LedgersQueryOutput::getDescription, GetLedgerResponse::setDescription)
            .addMappings(mapper -> mapper.skip(GetLedgerResponse::setLedgerItems))
            .addMapping(LedgersQueryOutput::getLedgerCreatedAt, GetLedgerResponse::setCreatedAt)
            .addMapping(LedgersQueryOutput::getLedgerUpdatedAt, GetLedgerResponse::setUpdatedAt);
        
        // Configure mapping from LedgersQueryOutput to LedgerItemsAggregate
        modelMapper.createTypeMap(LedgersQueryOutput.class, LedgerItemsAggregate.class)
            .addMapping(LedgersQueryOutput::getCoa, LedgerItemsAggregate::setCoa)
            .addMapping(LedgersQueryOutput::getAmount, LedgerItemsAggregate::setAmount)
            .addMapping(LedgersQueryOutput::getType, LedgerItemsAggregate::setType)
            .addMapping(LedgersQueryOutput::getLedgerCreatedAt, LedgerItemsAggregate::setCreatedAt)
            .addMapping(LedgersQueryOutput::getLedgerUpdatedAt, LedgerItemsAggregate::setUpdatedAt);

        modelMapper.validate();
        return modelMapper;
    }
}
