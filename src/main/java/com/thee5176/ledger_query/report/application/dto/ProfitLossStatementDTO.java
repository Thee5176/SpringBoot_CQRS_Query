package com.thee5176.ledger_query.report.application.dto;

import java.util.Map;

public record ProfitLossStatementDTO(
    Map<Integer, Double> revenueSettlement,
    Map<Integer, Double> expensesSettlement
) {
    public Double profitLossCheck() {
        double totalRevenue = revenueSettlement.values()
            .stream().mapToDouble(Double::doubleValue).sum();
        
        double totalExpenses = expensesSettlement.values()
            .stream().mapToDouble(Double::doubleValue).sum();
        return totalRevenue - totalExpenses;
    }
}
