package com.thee5176.ledger_query.Application.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.ledger_query.Application.dto.AvailableCodeOfAccountDto;
import com.thee5176.ledger_query.Infrastructure.repository.CodeOfAccountRepository;
import lombok.AllArgsConstructor;


@RestController
@CrossOrigin(origins = {
    "http://localhost:5173",  // local deployment
    "http://localhost:8183"   // docker deployment
})
@AllArgsConstructor
public class LedgersUIController {
    
    private final CodeOfAccountRepository codeOfAccountRepository;
    
    @PostMapping("available-coa/json")
    public List<AvailableCodeOfAccountDto> getAvailableCoa() {
        return codeOfAccountRepository.getAvailableCoa();
    }
}