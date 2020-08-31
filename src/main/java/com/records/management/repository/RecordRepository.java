package com.records.management.repository;

import com.records.management.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Bohdan Skrypnyk (bohdan.skrypnyk@yahoo.com)
 */
@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
