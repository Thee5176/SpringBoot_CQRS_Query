package com.thee5176.ledger_query.Domain.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.thee5176.ledger_query.Application.dto.GetLedgerResponse;
import com.thee5176.ledger_query.Application.dto.LedgerItemsAggregate;
import com.thee5176.ledger_query.Application.dto.LedgersQueryOutput;
import com.thee5176.ledger_query.Infrastructure.repository.LedgersRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class LedgersQueryService {
    private final ModelMapper modelMapper;
    private final LedgersRepository ledgersRepository;

    @Transactional(readOnly = true)
    public List<GetLedgerResponse> getAllLedgers() {
        List<LedgersQueryOutput> queryOutputs = ledgersRepository.getAllLedgersDTO();
        log.info("Query Outputs: " + queryOutputs);

        // get all ledgers from query
        List<GetLedgerResponse> response = queryOutputs.stream()
            .map(output -> modelMapper.map(output, GetLedgerResponse.class))
            .distinct()
            .toList();
        
        // create a map of items aggregate by ledgerId
        Map<UUID, List<LedgerItemsAggregate>> itemsAggregateByLedgerId = queryOutputs.stream()
            .filter(output -> output.getLedgerId() != null)
            .collect(Collectors.groupingBy(
            LedgersQueryOutput::getLedgerId,
            Collectors.mapping(output -> modelMapper.map(output, LedgerItemsAggregate.class), Collectors.toList())
            ));

        // set list of ledger items in each response by ledgerID
        response.forEach(ledger -> ledger.setLedgerItems(itemsAggregateByLedgerId.get(ledger.getLedgerId())));

        return response;
    }

    @Transactional(readOnly = true)
    public GetLedgerResponse getLedgerById(UUID id) {
        List<LedgersQueryOutput> queryOutput = ledgersRepository.getLedgerDTOById(id);
        log.info("Query Outputs: " + queryOutput);

        // map the item to GetLedgerResponse
        GetLedgerResponse response = queryOutput.stream()
            .map(output -> modelMapper.map(output, GetLedgerResponse.class))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Ledger not found with id: " + id));

        // set list of ledger items in response checking by ledgerId
        response.setLedgerItems( queryOutput.stream()
            .map(output -> modelMapper.map(output, LedgerItemsAggregate.class))
            .toList());
        
        return response;
    }
}