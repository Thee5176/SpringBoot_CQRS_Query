package com.thee5176.record.springboot_cqrs_query.Application.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import com.thee5176.record.springboot_cqrs_query.Domain.model.enums.BalanceType;

public record RecordDTO(
    UUID transaction_id,
    Date date,
    String description,
    BalanceType type,
    int coa,
    Double amount,
    LocalDateTime created_at,
    LocalDateTime updated_at
) {
    
}
