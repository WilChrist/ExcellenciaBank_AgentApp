package com.jsfstarterproject.models;


public class Refill extends Operation {
    private String reveiverNumber;
    private Double Amount;

    public String getReveiverNumber() {
        return reveiverNumber;
    }

    public void setReveiverNumber(String reveiverNumber) {
        this.reveiverNumber = reveiverNumber;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

}
