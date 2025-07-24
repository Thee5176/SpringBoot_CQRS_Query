package com.thee5176.ledger_query.Domain.service;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import com.thee5176.ledger_query.Application.dto.LedgersQueryOutput;
import com.thee5176.ledger_query.Infrastructure.repository.LedgersRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LedgerQueryService {
    private final LedgersRepository ledgersRepository;

    public List<LedgersQueryOutput> getAllLedgers() {
        return ledgersRepository.getAllLedgersDTO();
    }

    public LedgersQueryOutput getLedgerById(UUID id) {
        return ledgersRepository.getLedgerDTOById(id);
    }
}
