package com.navi.stocksaleprocessor.model;

public class SaleNote {

    private String buyOrderId;
    private Float sellPrice;
    private Integer quantity;
    private String sellOrderId;


    public SaleNote() {
    }

    public SaleNote(String buyOrderId, Float sellPrice, Integer quantity, String sellOrderId) {
        this.buyOrderId = buyOrderId;
        this.sellPrice = sellPrice;
        this.quantity = quantity;
        this.sellOrderId = sellOrderId;
    }

    public String getBuyOrderId() {
        return buyOrderId;
    }

    public void setBuyOrderId(String buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public Float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(Float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSellOrderId() {
        return sellOrderId;
    }

    public void setSellOrderId(String sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    @Override
    public String toString() {
        return  "" + buyOrderId +
                " " + String.format("%.02f", sellPrice) +
                " " + quantity +
                " " + sellOrderId;
    }
}
