package com.thee5176.record.springboot_cqrs_query.Application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateTransactionDTO(
    LocalDate date,
    String description,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {

}
