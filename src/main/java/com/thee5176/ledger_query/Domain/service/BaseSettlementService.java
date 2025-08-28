package com.thee5176.ledger_query.Domain.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.thee5176.ledger_query.Application.dto.LedgerBalanceSattlementDTO;
import com.thee5176.ledger_query.Domain.model.enums.Element;
import com.thee5176.ledger_query.Infrastructure.repository.AccountingSettlementRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BaseSettlementService {
    private final AccountingSettlementRepository accountingSettlementRepository;

    public Map<Integer, Double> settle(Element elementId) {
        List<LedgerBalanceSattlementDTO> elementItem = accountingSettlementRepository.findByElementId(elementId);

        // Group LedgerItems by COA
        Map<Integer, List<LedgerBalanceSattlementDTO>> assetItemByCoaMap = elementItem.stream()
            .collect(Collectors.groupingBy(LedgerBalanceSattlementDTO::coa));

        // Compute sum of balances per COA
        return assetItemByCoaMap.entrySet().stream()
        .collect(Collectors.toMap(
            Map.Entry::getKey,
            e -> e.getValue().stream()
            .mapToDouble(dto -> {
                // Rules: 
                // - if the COA balance side and the ledger item side are the same, add the amount.
                // - if different, subtract the amount.
                boolean sameSide = dto.accountBalanceType().equals(dto.ledgerItemBalanceType());
                return sameSide ? dto.balance() : -dto.balance();
                })
                .sum()
        ));
    }
}