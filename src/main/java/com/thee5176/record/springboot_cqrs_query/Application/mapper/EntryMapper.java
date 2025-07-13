package com.thee5176.record.springboot_cqrs_query.Application.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.record.springboot_cqrs_query.Application.dto.CreateEntryDTO;
import com.thee5176.record.springboot_cqrs_query.Domain.model.Entries;

@Service
public class EntryMapper {
    final ModelMapper modelMapper;

    public EntryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Entries map(CreateEntryDTO createEntrysDTO) {
        return modelMapper.map(createEntrysDTO, Entries.class);
    }
}