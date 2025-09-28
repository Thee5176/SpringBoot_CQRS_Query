package com.thee5176.ledger_query.report;

import java.util.Map;

public record BalanceSheetDTO (
    Map<Integer, Double> assetsSettlement,
    Map<Integer, Double> liabilitiesSettlement,
    Map<Integer, Double> equitySettlement
) {
    public Double balanceCheck() {
        double totalAssets = assetsSettlement.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalLiabilities = liabilitiesSettlement.values().stream().mapToDouble(Double::doubleValue).sum();
        double totalEquity = equitySettlement.values().stream().mapToDouble(Double::doubleValue).sum();
        return totalAssets - (totalLiabilities + totalEquity);
    }
}
