package com.thee5176.ledger_query.report;

import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.Element;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class LedgerSettlementController {
    private final BaseSettlementService baseSettlementService;
    private final FinancialStatementService financialStatementService;
    
    @GetMapping("/api/sattle")
    public Map<Integer, Double> settleLedger(@RequestParam Element elementId) {
        return baseSettlementService.settle(elementId);
    }
    @GetMapping("/api/balance-sheet")
    public BalanceSheetDTO getBalanceSheet() {
        return financialStatementService.generateBalanceSheet();
    }
    
    @GetMapping("/api/profit-loss-statement")
    public ProfitLossStatementDTO getProfitLossStatementDTO() {
        return financialStatementService.generateProfitLossStatement();
    }    
}
