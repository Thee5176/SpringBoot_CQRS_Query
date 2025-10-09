package com.thee5176.ledger_query.report.domain.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.thee5176.ledger_query.record.domain.model.accounting.enums.Element;
import com.thee5176.ledger_query.report.application.dto.BaseSattlementDTO;
import com.thee5176.ledger_query.report.repository.AccountingSettlementRepository;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class BaseSettlementService {
    private final AccountingSettlementRepository accountingSettlementRepository;

    public Map<Integer, Double> settle(@NotNull Element elementId) {
        List<BaseSattlementDTO> elementItem = accountingSettlementRepository.findByElementId(elementId);

        // Group LedgerItems by COA
        Map<Integer, List<BaseSattlementDTO>> assetItemByCoaMap = elementItem.stream()
            .collect(Collectors.groupingBy(BaseSattlementDTO::coa));

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