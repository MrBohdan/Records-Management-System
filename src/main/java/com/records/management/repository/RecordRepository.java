package com.records.management.repository;

import com.records.management.model.Record;
import com.records.management.util.CountTotalParValueUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository of the model {@link Record}
 *
 * @author Bohdan Skrypnyk (bohdan.skrypnyk@yahoo.com)
 */
@Repository
public interface RecordRepository extends JpaRepository<Record, Long>, CountTotalParValueUtil {

    @Override
    Page<Record> findAll(Pageable pageable);

    Optional<Record> findRecordByUsreou(Long usreou);
}
