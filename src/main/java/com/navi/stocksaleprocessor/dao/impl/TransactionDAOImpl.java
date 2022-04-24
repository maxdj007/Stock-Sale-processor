package com.navi.stocksaleprocessor.dao.impl;

import com.navi.stocksaleprocessor.common.TransactionType;
import com.navi.stocksaleprocessor.dao.TransactionDAO;
import com.navi.stocksaleprocessor.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransactionDAOImpl implements TransactionDAO {

    private static final List<Transaction> transactions = new ArrayList<>();

    @Override
    public Optional<Transaction> getById(String orderId) {
        return transactions.stream().filter(transaction -> transaction.getOrderId().equals(orderId)).findAny();
    }

    @Override
    public List<Transaction> getByTypeAndStock(TransactionType transactionType, String stock) {
        return transactions.stream().filter(transaction -> transaction.getStock().equals(stock))
                .filter(transaction -> transaction.getTransactionType().equals(transactionType))
                .collect(Collectors.toList());
    }

    @Override
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
