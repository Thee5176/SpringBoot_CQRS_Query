package com.thee5176.ledger_query.report.application.validation;

import java.math.BigDecimal;
import java.util.List;

import com.thee5176.ledger_query.record.domain.model.enums.BalanceType;
import com.thee5176.ledger_query.report.application.dto.LedgerItemsDTO;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;

public class NonZeroBalanceCheckValidator implements ConstraintValidator<NonZeroBalanceCheck, List<LedgerItemsDTO>> {

    @Override
    public void initialize(NonZeroBalanceCheck constraintAnnotation) {
    }

    @Override
    public boolean isValid(@NotNull List<LedgerItemsDTO> ledgerItems, ConstraintValidatorContext context) {

        return ledgerItems.stream()
                .map(item -> {
                    double amount = item.getBalanceType() == BalanceType.Credit ? item.getAmount() : -item.getAmount();
                    return BigDecimal.valueOf(amount);
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .compareTo(BigDecimal.ZERO) == 0;

    }
}
