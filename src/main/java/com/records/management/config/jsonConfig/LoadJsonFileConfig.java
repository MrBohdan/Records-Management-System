package com.records.management.config.jsonConfig;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.records.management.controller.RecordController;
import com.records.management.model.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Configuration to load json data from file to database.
 *
 * @author Bohdan Skrypnyk (bohdan.skrypnyk@yahoo.com)
 */
@Configuration
public class LoadJsonFileConfig implements CommandLineRunner {

    private final RecordController recordController;

    @Autowired
    public LoadJsonFileConfig(RecordController recordController) {
        this.recordController = recordController;
    }

    @Override
    public void run(String... args) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Record>> typeReference = new TypeReference<List<Record>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/records.json");
        try {
            List<Record> customer = objectMapper.readValue(inputStream, typeReference);
            recordController.insertListOfRecord(customer);
        } catch (IOException e) {
            System.out.println("Not found : " + e);
        }
    }
}
