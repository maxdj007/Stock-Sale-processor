package com.navi.stocksaleprocessor.service.impl;

import com.navi.stocksaleprocessor.model.Transaction;
import com.navi.stocksaleprocessor.service.FileHandler;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TypedFileManagerImplTest {

    @Mock
    private FileHandler mockFileHandler;

    @InjectMocks
    private TypedFileManagerImpl typedFileManagerImplUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testReadFromFile() {
        // Setup
        List<String[]> list = new ArrayList<>();
        list.add(new String[]{"#1", "09:45", "BAC", "sell", "200.12", "100"});
        when(mockFileHandler.readData("name")).thenReturn(list);

        // Run the test
        final List<Transaction> result = typedFileManagerImplUnderTest.readFromFile("name");

        // Verify the results
        Assert.assertEquals(1, result.size());
    }
}
