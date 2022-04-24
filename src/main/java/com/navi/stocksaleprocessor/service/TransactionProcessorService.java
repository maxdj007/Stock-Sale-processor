package com.navi.stocksaleprocessor.service;

import com.navi.stocksaleprocessor.common.TransactionType;
import com.navi.stocksaleprocessor.dao.TransactionDAO;
import com.navi.stocksaleprocessor.model.SaleNote;
import com.navi.stocksaleprocessor.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionProcessorService {

    private List<SaleNote> saleNotes = new ArrayList<>();

    @Autowired
    private TypedFileManager<Transaction> typedFileManager;

    @Autowired
    private TransactionDAO transactionDAO;

    public void processTransactionsFromFile(String fileName){

        List<Transaction> transactions = typedFileManager.readFromFile(fileName);
        transactions.forEach(transaction -> {
            if(TransactionType.sell.equals(transaction.getTransactionType())){
                processSellOrder(transaction);
            } else {
                processBuyOrder(transaction);
            }
        });
        printAllSaleNotes();
    }

    private void processSellOrder(Transaction transaction){
        List<Transaction> transactions = transactionDAO.getByTypeAndStock(TransactionType.buy, transaction.getStock());
        if(!transactions.isEmpty()){
            transactions.forEach(availableTransaction -> {
                if(availableTransaction.getPrice() >= transaction.getPrice() && availableTransaction.getAvailableQuantity() > 0 && transaction.getAvailableQuantity() > 0){
                    if(availableTransaction.getAvailableQuantity() >= transaction.getAvailableQuantity()){
                        saleNotes.add(generateSaleNote(availableTransaction.getOrderId()
                                , transaction.getOrderId(), transaction.getAvailableQuantity(), transaction.getPrice()));
                        availableTransaction.setAvailableQuantity(availableTransaction.getAvailableQuantity() - transaction.getAvailableQuantity());
                        transaction.setAvailableQuantity(0);
                    } else {
                        saleNotes.add(generateSaleNote(availableTransaction.getOrderId()
                                , transaction.getOrderId(), availableTransaction.getAvailableQuantity(), transaction.getPrice()));
                        transaction.setAvailableQuantity(transaction.getAvailableQuantity() - availableTransaction.getAvailableQuantity());
                        availableTransaction.setAvailableQuantity(0);
                    }
                }
            });
        }
        if(transaction.getAvailableQuantity() > 0){
            transactionDAO.addTransaction(transaction);
        }
    }

    private void processBuyOrder(Transaction transaction){
        List<Transaction> transactions = transactionDAO.getByTypeAndStock(TransactionType.sell, transaction.getStock());
        if(!transactions.isEmpty()){
            transactions.forEach(availableTransaction -> {
                if(transaction.getPrice() >= availableTransaction.getPrice() && availableTransaction.getAvailableQuantity() > 0 && transaction.getAvailableQuantity() > 0){
                    if(availableTransaction.getAvailableQuantity() >= transaction.getAvailableQuantity()){
                        saleNotes.add(generateSaleNote(transaction.getOrderId()
                                , availableTransaction.getOrderId(), transaction.getAvailableQuantity(), availableTransaction.getPrice()));
                        availableTransaction.setAvailableQuantity(availableTransaction.getAvailableQuantity() - transaction.getAvailableQuantity());
                        transaction.setAvailableQuantity(0);
                    } else {
                        saleNotes.add(generateSaleNote(transaction.getOrderId()
                                , availableTransaction.getOrderId(), availableTransaction.getAvailableQuantity(), availableTransaction.getPrice()));
                        transaction.setAvailableQuantity(transaction.getAvailableQuantity() - availableTransaction.getAvailableQuantity());
                        availableTransaction.setAvailableQuantity(0);

                    }
                }
            });
        }
        if(transaction.getAvailableQuantity() > 0){
            transactionDAO.addTransaction(transaction);
        }
    }

    private void printAllSaleNotes(){
        if(!saleNotes.isEmpty()){
            saleNotes.forEach(System.out::println);
        }
    }

    private SaleNote generateSaleNote(String buyOrderId, String sellOrderId, Integer quantity, Float sellPrice){
        SaleNote saleNote = new SaleNote();
        saleNote.setBuyOrderId(buyOrderId);
        saleNote.setSellPrice(sellPrice);
        saleNote.setQuantity(quantity);
        saleNote.setSellOrderId(sellOrderId);
        return saleNote;
    }

}
