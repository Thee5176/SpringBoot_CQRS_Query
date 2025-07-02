package com.thee5176.record.springboot_cqrs_query.Application.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thee5176.record.springboot_cqrs_query.Domain.model.Entries;
import com.thee5176.record.springboot_cqrs_query.Infrastructure.repository.EntryRepository;

@RestController
@RequestMapping("/entities")
public class EntryController{
    @Autowired
    EntryRepository entryRepository;

    @GetMapping
    public List<Entries> getEntry() {
        return entryRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<String> createEntry(@RequestBody Entries entry) {
        entryRepository.saveAndFlush(entry);
        
        return ResponseEntity.ok("Entry created succesfully");
    }

    @PutMapping
    public ResponseEntity<String> updateEntry(@RequestParam UUID uuid, @RequestBody Entries entry) {
        entry.setId(uuid);
        entryRepository.save(entry);
        
        return ResponseEntity.ok("Entry updated succesfully");
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEntry(@RequestParam UUID uuid) {
        entryRepository.deleteById(uuid);

        return ResponseEntity.ok("Entry deleted succesfully");
    }

}