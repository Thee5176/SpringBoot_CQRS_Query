package com.thee5176.ledger_query.report.application.controller;

import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.Element;
import com.thee5176.ledger_query.report.application.dto.BalanceSheetDTO;
import com.thee5176.ledger_query.report.application.dto.BaseSattlementDTO;
import com.thee5176.ledger_query.report.application.dto.ProfitLossStatementDTO;
import com.thee5176.ledger_query.report.domain.service.BaseSettlementService;
import com.thee5176.ledger_query.report.domain.service.FinancialStatementService;
import com.thee5176.ledger_query.report.repository.AccountingSettlementRepository;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class LedgerSettlementController {
    private final AccountingSettlementRepository accountingSettlementRepository;
    private final BaseSettlementService baseSettlementService;
    private final FinancialStatementService financialStatementService;

    @GetMapping("/api/test")
    public List<BaseSattlementDTO> testEndpoint(@RequestParam String elementNameString) {
        Element element = Element.lookupLiteral(elementNameString);
        return accountingSettlementRepository.findByElementId(element);
    }

    @GetMapping("/api/sattle")
    public Map<Integer, Double> settleLedger(@RequestParam String elementId) {
        return baseSettlementService.settle(Element.lookupLiteral(elementId));
    }

    @GetMapping("/api/balance-sheet-statement")
    public BalanceSheetDTO getBalanceSheet() {
        // need Net Income calculation from ProfitLossStatementService
        ProfitLossStatementDTO profitLossStatement = financialStatementService.generateProfitLossStatement();
        Double netIncome = profitLossStatement.getNetIncome();

        return financialStatementService.generateBalanceSheet();
    }
    
    @GetMapping("/api/profit-loss-statement")
    public ProfitLossStatementDTO getProfitLossStatementDTO() {
        return financialStatementService.generateProfitLossStatement();
    }    
}
