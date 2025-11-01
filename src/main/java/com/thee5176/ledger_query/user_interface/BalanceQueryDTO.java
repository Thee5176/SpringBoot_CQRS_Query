package com.thee5176.ledger_query.user_interface;

public record BalanceQueryDTO(
    Integer coa,
    String coaBalanceType,
    String balanceType,
    Double balance
) {}