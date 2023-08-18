package com.neuedatraining.CreditCardApplication.dto;

public class Merchantspend {
    private String merchant;
    private double sales;

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public double getSales() {
        return sales;
    }

    public void setSales(double sales) {
        this.sales = sales;
    }
}
