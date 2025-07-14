package com.thee5176.record.springboot_cqrs_query.Application.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.thee5176.record.springboot_cqrs_query.Application.dto.CreateRecordDTO;
import com.thee5176.record.springboot_cqrs_query.Domain.service.RecordReplicatorService;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/record")
@AllArgsConstructor
public class RecordController {
    private final RecordReplicatorService recordReplicatorService;

    @PostMapping
    public ResponseEntity<String> newBookrecord(@RequestBody CreateRecordDTO createRecordDTO) {
        recordReplicatorService.replicateRecord(createRecordDTO);

        return ResponseEntity.ok("Succesfully create new bookrecord");
    }
}