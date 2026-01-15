package com.thee5176.ledger_query.user_interface;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RestController
@AllArgsConstructor
@Slf4j
public class LedgersUIController {
    
    private final CodeOfAccountRepository codeOfAccountRepository;
    private final BalanceQueryService balanceQueryService;
    
    @PostMapping("/available-coa/json")
    public List<AvailableCodeOfAccountDto> getAvailableCoa() {
        log.info("ENTERING getAvailableCoa endpoint");
        try {
            List<AvailableCodeOfAccountDto> result = codeOfAccountRepository.getAvailableCoa();
            log.info("Successfully retrieved {} COA records", result.size());
            return result;
        } catch (Exception e) {
            log.error("ERROR in getAvailableCoa: {}", e.getMessage(), e);
            throw e;
        }
    }

    @PostMapping("/balance/json")
    public List<BalanceQueryOutput> getBalancePerAccount(@RequestBody List<Integer> listOfCoa) {
        log.info("ENTERING getBalancePerAccount endpoint with {} COA codes", listOfCoa.size());
        try {
            List<BalanceQueryOutput> result = balanceQueryService.getBalancePerAccount(listOfCoa);
            log.info("Successfully retrieved balance for {} accounts", result.size());
            return result;
        } catch (Exception e) {
            log.error("ERROR in getBalancePerAccount: {}", e.getMessage(), e);
            throw e;
        }
    }
}