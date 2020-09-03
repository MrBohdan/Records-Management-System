package com.records.management.controller;

import com.records.management.exception.ApiRequestException;
import com.records.management.model.Record;
import com.records.management.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Controller to get web requests of the model {@link Record}.
 *
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

    /**
     * Insertion of a new record.
     *
     * @param record - define a model {@link Record}.
     * @return - return saved record.
     */
    @PostMapping("/add")
    private @ResponseBody
    Record insertRecord(@Validated @NonNull @RequestBody Record record) {
        record.setTotalParValue(recordRepository.countTotalParValueUtil(record.getSharesAmount(), record.getParValue()));
        record.setStatus("active");
        return recordRepository.save(record);
    }

    /**
     * Updating of an old record.
     *
     * @param usreou - Unified State Register (A unique value for each record).
     * @param record - define a model {@link Record}.
     * @return - return saved updated record.
     */
    @PutMapping("/update/usreou={usreou}")
    private @ResponseBody
    Record updateRecord(@NonNull @PathVariable("usreou") Long usreou, @Validated @NonNull @RequestBody Record record) {
        try {
            return recordRepository.save(setUpNewRecord(usreou, record));
        } catch (Exception e) {
            throw new ApiRequestException("Unified state register not found!");
        }
    }

    /**
     * Insertion of the list of records.
     *
     * @param record - iterable elements sequence of a model {@link Record}.
     * @return - return saved collection.
     */
    @PostMapping("/add/list")
    public @ResponseBody
    Collection<Record> insertListOfRecord(@Validated @NonNull @RequestBody Iterable<Record> record) {
        for (Record r : record) {
            r.setTotalParValue(recordRepository.countTotalParValueUtil(r.getSharesAmount(), r.getParValue()));
            r.setStatus("active");
        }
        return recordRepository.saveAll(record);
    }

    /**
     * Get the peggable list of records.
     *
     * @param pageable - apply pagination.
     * @return - return all records, divided by pages with the default amount of 10 records on each page.
     */
    @GetMapping(value = "/get/")
    private @ResponseBody
    Page<Record> findAll(@PageableDefault(size = 10) Pageable pageable) {
        return recordRepository.findAll(pageable);
    }

    /**
     * Get the peggable list of records sorted by column.
     *
     * @param column - column name.
     * @param page   - requested page.
     * @param size   - requested size of page.
     * @return - return all records, with the default amount of 10 records on each page.
     */
    @GetMapping(value = "/sort/column={column}")
    private @ResponseBody
    Page<Record> sortByColumn(@PathVariable(name = "column", required = false) String column,
                              @RequestParam(name = "page", defaultValue = "0") int page,
                              @RequestParam(name = "size", defaultValue = "10") int size) {
        return recordRepository.findAll(PageRequest.of(page, size, Sort.by(column)));
    }

    /**
     * Handle request where a column name was not provided.
     *
     * @return - return list of all records.
     */
    @GetMapping(value = {"/sort", "/sort/column="})
    private @ResponseBody
    List<Record> findAllRecord() {
        return recordRepository.findAll();
    }

    /**
     * Find a record by usreou.
     *
     * @param usreou - Unified State Register (A unique value for each record).
     * @return - return founded record.
     */
    @GetMapping(value = "/get/usreou={usreou}")
    private Optional<Record> findRecordByUsreou(@NonNull @PathVariable("usreou") Long usreou) {
        try {
            return recordRepository.findRecordByUsreou(usreou);
        } catch (Exception e) {
            throw new ApiRequestException("Unified state register not found!");
        }
    }

    /**
     * Change the status of a record, from 'active' to 'deleted'.
     *
     * @param usreou - Unified State Register (A unique value for each record).
     * @return - return founded record.
     */
    @DeleteMapping(value = "/delete/{usreou}")
    private @ResponseBody
    Record removeRecordByUsreou(@NonNull @PathVariable("usreou") Long usreou) {
        try {
            Record record = recordRepository.findRecordByUsreou(usreou).get();
            record.setStatus("deleted");
            return recordRepository.save(record);
        } catch (Exception e) {
            throw new ApiRequestException("Unified state register not found!");
        }
    }

    /**
     * Setup an updated record.
     *
     * @param usreou - Unified State Register (A unique value for each record).
     * @param record - define a model {@link Record}.
     * @return - return an updated record.
     */
    private Record setUpNewRecord(Long usreou, Record record) {
        Record newRecord = recordRepository.findRecordByUsreou(usreou).get();
        newRecord.setComment(record.getComment());
        newRecord.setSharesAmount(record.getSharesAmount());
        newRecord.setTotalParValue(record.getParValue());
        newRecord.setPayDate(LocalDateTime.now());
        return newRecord;
    }
}
