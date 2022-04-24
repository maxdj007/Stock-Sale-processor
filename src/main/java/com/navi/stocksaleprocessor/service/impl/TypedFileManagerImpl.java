package com.navi.stocksaleprocessor.service.impl;

import com.navi.stocksaleprocessor.common.TransactionType;
import com.navi.stocksaleprocessor.model.Transaction;
import com.navi.stocksaleprocessor.service.FileHandler;
import com.navi.stocksaleprocessor.service.TypedFileManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypedFileManagerImpl implements TypedFileManager<Transaction> {

    @Autowired
    private FileHandler fileHandler;

    @Override
    public List<Transaction> readFromFile(String name) {
        List<Transaction> transactions = new ArrayList<>();
        List<String[]> dataLines = fileHandler.readData(name);
        for(String[] dataLine : dataLines){
            if(dataLine.length >= 6) {
                Transaction transaction = new Transaction();
                transaction.setOrderId(dataLine[0]);
                transaction.setTime(dataLine[1]);
                transaction.setStock(dataLine[2]);
                transaction.setTransactionType(TransactionType.valueOf(dataLine[3]));
                transaction.setPrice(Float.valueOf(dataLine[4]));
                transaction.setQuantity(Integer.valueOf(dataLine[5]));
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
