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

        public List<BalanceQueryDTO> getBalancePerAccount(List<String> listOfCoa) {

        // TODO: Handle non-matching balance types
        List<BalanceQueryDTO> rawTransaction = codeOfAccountRepository.getBalancePerAccount(listOfCoa);

        Map<String, Double> accountBalances = new HashMap<>();
        
        rawTransaction.forEach( transaction -> {
            Double finalBalance = (transaction.coaBalanceType().equals(transaction.balanceType()))
                ? transaction.balance() * -1
                : transaction.balance();

            accountBalances.merge(transaction.coa(), finalBalance, Double::sum);
        });

        return accountBalances.entrySet().stream()
            .map(entry -> new BalanceQueryDTO(entry.getKey(), "", "", entry.getValue()))
            .toList();
    }
}