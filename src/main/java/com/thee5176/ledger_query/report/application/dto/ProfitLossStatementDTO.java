package com.thee5176.ledger_query.report.application.dto;

import java.util.Map;

public record ProfitLossStatementDTO(
    Map<Integer, Double> revenue,
    Map<Integer, Double> expenses
) {
    public Double getNetIncome() {
        double totalRevenue = revenue.values()
            .stream().mapToDouble(Double::doubleValue).sum();
        
        double totalExpenses = expenses.values()
            .stream().mapToDouble(Double::doubleValue).sum();
        return totalRevenue - totalExpenses;
    }
}
