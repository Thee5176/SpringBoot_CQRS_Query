package com.thee5176.record.springboot_cqrs_query.Application.dto;

import java.time.LocalDateTime;

import com.thee5176.record.springboot_cqrs_query.Domain.model.enums.BalanceType;

public record CreateEntryDTO(
    Integer coa,
    Double amount,
    BalanceType type,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    
}
