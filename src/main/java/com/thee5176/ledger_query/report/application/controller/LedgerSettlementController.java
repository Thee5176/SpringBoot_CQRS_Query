package com.thee5176.ledger_query.report.application.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.report.application.dto.BalanceSheetDTO;
import com.thee5176.ledger_query.report.application.dto.ProfitLossDTO;
import com.thee5176.ledger_query.report.domain.service.FinancialStatementService;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class LedgerSettlementController {
    private final FinancialStatementService financialStatementService;

    @GetMapping("/api/balance-sheet-statement")
    public BalanceSheetDTO getBalanceSheet(@AuthenticationPrincipal UserDetails userDetails) {
        return financialStatementService.generateBalanceSheetStatement(userDetails.getUsername());
    }
    
    @GetMapping("/api/profit-loss-statement")
    public ProfitLossDTO getProfitLossDTO(@AuthenticationPrincipal UserDetails userDetails) {
        return financialStatementService.generateProfitLossStatement(userDetails.getUsername());
    }    
}
