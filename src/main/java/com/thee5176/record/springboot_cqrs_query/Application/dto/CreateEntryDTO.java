package com.thee5176.record.springboot_cqrs_query.Application.dto;

import java.util.UUID;
import com.thee5176.record.springboot_cqrs_query.Domain.model.enums.BalanceType;

public record CreateEntryDTO(
    UUID id,
    UUID transactionId,
    Integer coa,
    Double amount,
    BalanceType type
) {
    
}
