package com.navi.stocksaleprocessor.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileHandlerImplTest {

    private FileHandlerImpl fileHandlerImplUnderTest;

    @BeforeEach
    void setUp() {

        fileHandlerImplUnderTest = new FileHandlerImpl();
    }

    @Test
    void testFileExists() {
        // Setup

        // Run the test
        final boolean result = fileHandlerImplUnderTest.fileExists("src/test/resources/testData.txt");

        // Verify the results
        assertTrue(result);
    }

    @Test
    void testReadData() {
        // Setup

        // Run the test
        final List<String[]> result = fileHandlerImplUnderTest.readData("src/test/resources/testData.txt");

        // Verify the results
        assertEquals(6, result.size());
    }
}
