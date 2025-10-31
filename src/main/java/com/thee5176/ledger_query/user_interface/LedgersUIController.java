package com.thee5176.ledger_query.user_interface;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
public class LedgersUIController {
    
    private final CodeOfAccountRepository codeOfAccountRepository;
    private final BalanceQueryService balanceQueryService;
    
    @PostMapping("/available-coa/json")
    public List<AvailableCodeOfAccountDto> getAvailableCoa() {
        return codeOfAccountRepository.getAvailableCoa();
    }

    @PostMapping("/balance/json")
    public List<BalanceQueryOutput> getBalancePerAccount(@RequestBody List<String> listOfCoa) {
        return balanceQueryService.getBalancePerAccount(listOfCoa);
    }
}