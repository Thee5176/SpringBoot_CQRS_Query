package com.thee5176.record.springboot_cqrs_query.model.enums;

@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public enum BalanceType {

    Debit("Debit"),

    Credit("Credit");

    private final String literal;

    private BalanceType(String literal) {
        this.literal = literal;
    }
}
