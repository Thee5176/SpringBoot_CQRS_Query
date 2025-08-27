package com.thee5176.ledger_query.Domain.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.thee5176.ledger_query.Application.dto.LedgerBalanceSattlementDTO;
import com.thee5176.ledger_query.Domain.model.enums.Element;
import com.thee5176.ledger_query.Infrastructure.repository.AccountingSettlementRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseSettlementService {
    private final AccountingSettlementRepository accountingSettlementRepository;

    public Map<Integer, Double> settle() {
        List<LedgerBalanceSattlementDTO> assetItems = accountingSettlementRepository.findByElementId(Element.Assets);

        // Group LedgerItems by COA
        Map<Integer, List<LedgerBalanceSattlementDTO>> assetItemByCoaMap = assetItems.stream()
            .collect(Collectors.groupingBy(LedgerBalanceSattlementDTO::coa));

        // Compute sum of balances per COA
        return assetItemByCoaMap.entrySet().stream()
            .collect(Collectors.toMap(
                Map.Entry::getKey,
                e -> e.getValue().stream()
                    .mapToDouble(LedgerBalanceSattlementDTO::balance)
                    .sum()
        ));
    }
}