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
            .addMapping(LedgersQueryOutput::ledgerId, GetLedgerResponse::setLedgerId)
            .addMapping(LedgersQueryOutput::date, GetLedgerResponse::setDate)
            .addMapping(LedgersQueryOutput::description, GetLedgerResponse::setDescription)
            .addMappings(mapper -> mapper.skip(GetLedgerResponse::setLedgerItems))
            .addMapping(LedgersQueryOutput::createdAt, GetLedgerResponse::setCreatedAt)
            .addMapping(LedgersQueryOutput::updatedAt, GetLedgerResponse::setUpdatedAt);
        
        // Configure mapping from LedgersQueryOutput to LedgerItemsAggregate
        modelMapper.createTypeMap(LedgersQueryOutput.class, LedgerItemsAggregate.class)
            .addMapping(LedgersQueryOutput::coa, LedgerItemsAggregate::setCoa)
            .addMapping(LedgersQueryOutput::amount, LedgerItemsAggregate::setAmount)
            .addMapping(LedgersQueryOutput::type, LedgerItemsAggregate::setType);

        modelMapper.validate();
        return modelMapper;
    }
}
