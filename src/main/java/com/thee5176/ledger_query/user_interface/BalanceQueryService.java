package com.thee5176.ledger_query.user_interface;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class BalanceQueryService {

    private final CodeOfAccountRepository codeOfAccountRepository;

        public BalanceQueryService(CodeOfAccountRepository codeOfAccountRepository) {
            this.codeOfAccountRepository = codeOfAccountRepository;
        }

        public List<BalanceQueryOutput> getBalancePerAccount(List<Integer> listOfCoa) {

        // TODO: The logic here is similar to BaseSettlementService.settle()!!
        List<BalanceQueryDTO> rawTransaction = codeOfAccountRepository.getBalancePerAccount(listOfCoa);

        Map<String, Double> accountBalances = new HashMap<>();
        
        rawTransaction.forEach( transaction -> {
            Double finalBalance = (transaction.coaBalanceType().equals(transaction.balanceType()))
                ? transaction.balance()
                : transaction.balance() * -1;

            accountBalances.merge(transaction.coa(), finalBalance, Double::sum);
        });

        return accountBalances.entrySet().stream()
            .map(entry -> new BalanceQueryOutput(entry.getKey(), entry.getValue()))
            .toList();
    }
}