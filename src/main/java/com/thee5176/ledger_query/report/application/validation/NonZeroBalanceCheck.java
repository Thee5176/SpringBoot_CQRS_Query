package com.thee5176.ledger_query.report.application.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = NonZeroBalanceCheckValidator.class)
public @interface NonZeroBalanceCheck {
    String message() default "Balance must be non-zero";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
