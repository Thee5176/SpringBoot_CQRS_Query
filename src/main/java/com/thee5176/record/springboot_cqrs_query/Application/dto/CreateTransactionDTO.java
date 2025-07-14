package com.thee5176.record.springboot_cqrs_query.Application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CreateTransactionDTO(
    UUID id,
    LocalDate date,
    String description
) {

}
