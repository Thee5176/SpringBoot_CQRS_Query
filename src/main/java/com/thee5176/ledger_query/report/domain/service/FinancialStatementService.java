package com.thee5176.ledger_query.report.domain.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.Element;
import com.thee5176.ledger_query.report.application.dto.BalanceSheetDTO;
import com.thee5176.ledger_query.report.application.dto.ProfitLossDTO;
import com.thee5176.ledger_query.report.repository.AccountingSettlementRepository;
import jakarta.validation.constraints.NotNull;

@Service
public class FinancialStatementService extends BaseSettlementService {

    public FinancialStatementService(
        AccountingSettlementRepository accountingSettlementRepository
    ) {
        super(accountingSettlementRepository);
    }

    public BalanceSheetDTO generateBalanceSheetStatement(@NotNull String username) {
        List<Element> associatedElements = List.of(Element.Assets, Element.Liabilities, Element.Equity);

        // map each Element to its settlement map with the settle method
        Map<Element, Map<Integer, Double>> elementMap = associatedElements.stream()
            .collect(Collectors.toMap(e -> e, e -> this.settle(username, e)));

        // Adjust Equity with Net Income from Profit and Loss Statement
        // COA ID 3110 represents Retained Earnings (Flyway V5)
        ProfitLossDTO profitLossDTO = this.generateProfitLossStatement(username);
        double netIncome = profitLossDTO.getNetIncome();
        
        Map<Integer, Double> equityMap = elementMap.get(Element.Equity);
        equityMap.put(3110, equityMap.getOrDefault(3110, 0.0) + netIncome);

        // populate DTO with each element settlements
        return new BalanceSheetDTO(elementMap.get(Element.Assets), elementMap.get(Element.Liabilities), elementMap.get(Element.Equity));
    }

    public ProfitLossDTO generateProfitLossStatement(@NotNull String username) {
        List<Element> associatedElements = List.of(Element.Revenue, Element.Expenses);

        // map each Element to its settlement map
        Map<Element, Map<Integer, Double>> elementMap = associatedElements.stream()
            .collect(Collectors.toMap(e -> e, e -> this.settle(username, e)));

        // populate DTO with each element settlements
        return new ProfitLossDTO(elementMap.get(Element.Revenue), elementMap.get(Element.Expenses));
    }
}
