package com.navi.stocksaleprocessor.service;

import com.navi.stocksaleprocessor.common.TransactionType;
import com.navi.stocksaleprocessor.dao.TransactionDAO;
import com.navi.stocksaleprocessor.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class TransactionProcessorServiceTest {

    @Mock
    private TypedFileManager<Transaction> mockTypedFileManager;
    @Mock
    private TransactionDAO mockTransactionDAO;

    @InjectMocks
    private TransactionProcessorService transactionProcessorServiceUnderTest;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testProcessTransactionsFromFile() {

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("#1", "09:45", "BAC", TransactionType.sell, Float.valueOf("200.12"), 100));

        when(mockTypedFileManager.readFromFile("name")).thenReturn(transactions);

        // Configure TransactionDAO.getByTypeAndStock(...).
        final List<Transaction> transactions1 = new ArrayList<>();
        when(mockTransactionDAO.getByTypeAndStock(TransactionType.sell, "BAC")).thenReturn(transactions1);

        // Run the test
        transactionProcessorServiceUnderTest.processTransactionsFromFile("name");

        // Verify the results
        verify(mockTransactionDAO).addTransaction(any(Transaction.class));
    }
}
