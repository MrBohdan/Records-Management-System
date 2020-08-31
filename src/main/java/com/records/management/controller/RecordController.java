package com.records.management.controller;

import com.records.management.model.Record;
import com.records.management.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * @author Bohdan Skrypnyk (bohdan.skrypnyk@yahoo.com)
 */
@RestController
@RequestMapping(value = "api/v1.0/record/")
@CrossOrigin("*")
public class RecordController {

    private final RecordRepository recordRepository;

    @Autowired
    public RecordController(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @PostMapping("/add")
    private @ResponseBody
    Record insertRecord(@Validated @NonNull @RequestBody Record record) {
        return recordRepository.save(record);
    }

    @GetMapping(value = "/get")
    public @ResponseBody
    Page<Record> findAll(Pageable pageable) {
        return recordRepository.findAll(pageable);
    }

    @GetMapping(value = "/get/{usreou}")
    public Optional<Record> findRecordByUsreou(@PathVariable("usreou") Long usreou) {
        return recordRepository.findRecordByUsreou(usreou);
    }

   /* @DeleteMapping(value = "/delete/{usreou}")
    public void removeRecordByUsreou(@PathVariable("usreou") Long usreou) {
        recordRepository.deleteRecordByUsreou(usreou);
    }*/
}
