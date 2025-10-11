package com.thee5176.ledger_query.report.application.dto;

import java.util.Map;

public record BalanceSheetDTO (
    Map<Integer, Double> assets,
    Map<Integer, Double> liabilities,
    Map<Integer, Double> equity
) {
    public Double balanceCheck() {
        double totalAssets = assets.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalLiabilities = liabilities.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalEquity = equity.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalAssets - (totalLiabilities + totalEquity);
    }
}
