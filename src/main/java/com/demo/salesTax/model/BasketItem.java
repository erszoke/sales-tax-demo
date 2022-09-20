package com.demo.salesTax.model;

import java.math.BigDecimal;

public class BasketItem {

    private int amount;
    private TaxRatePct taxRatePct;
    private  TaxRatePct additionalTaxRatePct;
    private  BigDecimal unitNetPrice;
    private BigDecimal taxSales;
    private BigDecimal grossPrice;
    private String product;

    public BasketItem(int amount, BigDecimal unitNetPrice, TaxRatePct taxRatePct) {
        this.amount = amount;
        this.taxRatePct = taxRatePct;
        this.unitNetPrice = unitNetPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TaxRatePct getTaxRate() {
        return taxRatePct;
    }

    public void setTaxRate(TaxRatePct taxRatePct) {
        this.taxRatePct = taxRatePct;
    }

    public TaxRatePct getAdditionalTaxRate() {
        return additionalTaxRatePct;
    }

    public void setAdditionalTaxRate(TaxRatePct additionalTaxRatePct) {
        this.additionalTaxRatePct = additionalTaxRatePct;
    }

    public BigDecimal getUnitNetPrice() {
        return unitNetPrice;
    }

    public void setUnitNetPrice(BigDecimal unitNetPrice) {
        this.unitNetPrice = unitNetPrice;
    }

    public BigDecimal getTaxSales() {
        return taxSales;
    }

    public void setTaxSales(BigDecimal taxSales) {
        this.taxSales = taxSales;
    }

    public BigDecimal getGrossPrice() {
        return grossPrice;
    }

    public void setGrossPrice(BigDecimal grossPrice) {
        this.grossPrice = grossPrice;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct() {
        return product;
    }
}
