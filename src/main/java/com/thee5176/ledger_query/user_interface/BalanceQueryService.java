package com.thee5176.ledger_query.user_interface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BalanceQueryService {

    private final CodeOfAccountRepository codeOfAccountRepository;

        public BalanceQueryService(CodeOfAccountRepository codeOfAccountRepository) {
            this.codeOfAccountRepository = codeOfAccountRepository;
        }

        @Transactional(readOnly = true)
        public List<BalanceQueryOutput> getBalancePerAccount(String userId,List<Integer> listOfCoa) {

            // TODO: The logic here is similar to BaseSettlementService.settle()!!
            if (listOfCoa == null || listOfCoa.isEmpty()) {
                return java.util.Collections.emptyList();
            }

            List<BalanceQueryDTO> rawTransaction = codeOfAccountRepository.getBalancePerAccount(userId, listOfCoa);
            if (rawTransaction == null) {
                rawTransaction = java.util.Collections.emptyList();
            }

            Map<Integer, Double> accountBalances = listOfCoa.stream()
                .collect(
                    HashMap::new,
                    (map, coa) -> map.put(coa, 0.0),
                    HashMap::putAll
                );
            
            rawTransaction.forEach( transaction -> {
                if (transaction == null) {
                    return;
                }

                Integer coaKey = transaction.coa();
                if (coaKey == null || !accountBalances.containsKey(coaKey)) {
                    return;
                }

                Double balanceValue = transaction.balance();
                double balance = (balanceValue != null) ? balanceValue : 0.0;

                boolean sameType = java.util.Objects.equals(transaction.coaBalanceType(), transaction.balanceType());
                double finalBalance = sameType ? balance : -balance;

                accountBalances.merge(coaKey, finalBalance, Double::sum);
            });

            return accountBalances.entrySet().stream()
                .map(entry -> new BalanceQueryOutput(entry.getKey(), entry.getValue()))
                .toList();
    }
}