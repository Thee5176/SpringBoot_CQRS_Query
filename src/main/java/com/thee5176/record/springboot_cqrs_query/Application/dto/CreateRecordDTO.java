package com.thee5176.record.springboot_cqrs_query.Application.dto;

import java.util.List;

public record CreateRecordDTO (
    CreateTransactionDTO createTransactionsDTO,
    List<CreateEntryDTO> createEntriesDTO
) {
    public CreateTransactionDTO getTransaction() {
        return createTransactionsDTO;
    }

    public List<CreateEntryDTO> getEntries() {
        return createEntriesDTO;
    }
}
