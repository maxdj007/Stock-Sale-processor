package com.navi.stocksaleprocessor.dao;

import com.navi.stocksaleprocessor.common.TransactionType;
import com.navi.stocksaleprocessor.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionDAO {

    Optional<Transaction> getById(String orderId);

    List<Transaction> getByTypeAndStock(TransactionType transactionType, String stock);

    void addTransaction(Transaction transaction);

}
