package com.thee5176.ledger_query.report.domain.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.Element;
import com.thee5176.ledger_query.report.application.dto.BalanceSheetDTO;
import com.thee5176.ledger_query.report.application.dto.ProfitLossStatementDTO;
import com.thee5176.ledger_query.report.repository.AccountingSettlementRepository;

@Service
public class FinancialStatementService extends BaseSettlementService {
    public FinancialStatementService(
        AccountingSettlementRepository accountingSettlementRepository) {
            super(accountingSettlementRepository);
    }

    public BalanceSheetDTO generateBalanceSheet() {
        List<Element> associatedElements = List.of(Element.Assets, Element.Liabilities, Element.Equity);

        // map each Element to its settlement map
        Map<Element, Map<Integer, Double>> elementMap = associatedElements.stream()
            .collect(Collectors.toMap(e -> e, this::settle));

        // populate DTO with per-element settlements
        return new BalanceSheetDTO(elementMap.get(Element.Assets), elementMap.get(Element.Liabilities), elementMap.get(Element.Equity));
    }

    public ProfitLossStatementDTO generateProfitLossStatement() {
        List<Element> associatedElements = List.of(Element.Revenue, Element.Expenses);

        // map each Element to its settlement map
        Map<Element, Map<Integer, Double>> elementMap = associatedElements.stream()
            .collect(Collectors.toMap(e -> e, this::settle));

        // populate DTO with per-element settlements
        return new ProfitLossStatementDTO(elementMap.get(Element.Revenue), elementMap.get(Element.Expenses));
    }
}
