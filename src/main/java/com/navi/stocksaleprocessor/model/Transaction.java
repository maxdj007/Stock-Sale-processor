package com.navi.stocksaleprocessor.model;

import com.navi.stocksaleprocessor.common.TransactionType;

public class Transaction {

    private String orderId;
    private String time;
    private String stock;
    private TransactionType transactionType;
    private Float price;
    private Integer quantity;
    private Integer availableQuantity;

    public Transaction() {
    }

    public Transaction(String orderId, String time, String stock, TransactionType transactionType, Float price, Integer quantity) {
        this.orderId = orderId;
        this.time = time;
        this.stock = stock;
        this.transactionType = transactionType;
        this.price = price;
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.availableQuantity = quantity;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}
