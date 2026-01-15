package com.thee5176.ledger_query.report.application.validation;

import java.util.List;
import com.thee5176.ledger_query.record.domain.model.enums.BalanceType;
import com.thee5176.ledger_query.report.application.dto.LedgerItemsDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.constraints.NotNull;


public class NonZeroBalanceCheckValidator implements ConstraintValidator<NonZeroBalanceCheck, List<LedgerItemsDTO>> {

    @Override
    public void initialize(NonZeroBalanceCheck constraintAnnotation) {}

    @Override
    public boolean isValid(@NotNull List<LedgerItemsDTO> ledgerItems, ConstraintValidatorContext context) {

        return ledgerItems.stream()
            // TODO: non-zero balance criterior
            // 1. find Credit and Debit BalanceType Summation
            .mapToDouble(item -> item.getBalanceType() == BalanceType.Credit ? item.getAmount() : -item.getAmount())
            .sum() == 0.0;
                
            // 2. 
            // 3. get normal balance type Summation and minus the other type Summation
            // 4. if the result is not zero, return false

    }    
}
