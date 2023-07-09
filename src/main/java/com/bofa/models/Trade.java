package com.bofa.models;

import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonGetter;

@Component
public class Trade {
    private long id;
    private String currencyOne;
    private String currencyTwo;
    private int quantity;
    private double price;
    public Trade() {
    
    }
    public Trade(long id, String currencyOne, String currencyTwo, int quantity, double price) {
        this.id = id;
        this.currencyOne = currencyOne;
        this.currencyTwo = currencyTwo;
        this.quantity = quantity;
        this.price = price;
    }
    @JsonGetter("identity")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @JsonGetter("fromCurrency")
    public String getCurrencyOne() {
        return currencyOne;
    }
    public void setCurrencyOne(String currencyOne) {
        this.currencyOne = currencyOne;
    }
    public String getCurrencyTwo() {
        return currencyTwo;
    }
    public void setCurrencyTwo(String currencyTwo) {
        this.currencyTwo = currencyTwo;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
