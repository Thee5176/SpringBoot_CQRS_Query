package com.thee5176.record.springboot_cqrs_query.Application.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CreateRecordDTO (
    CreateTransactionDTO transaction,
    List<CreateEntryDTO> entries,
    LocalDateTime timestamp
) {
    
}