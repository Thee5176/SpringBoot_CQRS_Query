package com.thee5176.ledger_query.Domain.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.thee5176.ledger_query.Application.dto.GetLedgerResponse;
import com.thee5176.ledger_query.Application.dto.LedgerItemsAggregate;
import com.thee5176.ledger_query.Application.dto.LedgersQueryOutput;
import com.thee5176.ledger_query.Infrastructure.repository.LedgersRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LedgerQueryService {
    private final ModelMapper modelMapper;
    private final LedgersRepository ledgersRepository;

    public List<GetLedgerResponse> getAllLedgers() {
        List<LedgersQueryOutput> queryOutputs = ledgersRepository.getAllLedgersDTO();
        
        // get all ledgers from query
        List<GetLedgerResponse> response = queryOutputs.stream()
            .map(output -> modelMapper.map(output, GetLedgerResponse.class))
            .distinct()
            .toList();
        
        // create a map of items aggregate by ledgerId
        Map<UUID, List<LedgerItemsAggregate>> itemsAggregateByLedgerId = queryOutputs.stream()
            .collect(Collectors.groupingBy(
                LedgersQueryOutput::ledgerId,
                Collectors.mapping(output -> modelMapper.map(output, LedgerItemsAggregate.class), Collectors.toList())
            ));

        // set list of ledger items in each response by ledgerID
        response.forEach(ledger -> ledger.setLedgerItems(itemsAggregateByLedgerId.get(ledger.getLedgerId())));

        return response;
    }

    public GetLedgerResponse getLedgerById(UUID id) {
        List<LedgersQueryOutput> queryOutput = ledgersRepository.getLedgerDTOById(id);
        
        // map the first item to GetLedgerResponse
        GetLedgerResponse response = modelMapper.map(queryOutput.get(0), GetLedgerResponse.class);

        // set list of ledger items in response checking by ledgerId
        response.setLedgerItems(queryOutput.stream()
            .filter(output -> output.ledgerId().equals(response.getLedgerId()))
            .map(output -> modelMapper.map(output, LedgerItemsAggregate.class))
            .toList());
        
        return response;
    }
}