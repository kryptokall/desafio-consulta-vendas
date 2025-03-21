package com.devsuperior.dsmeta.dto;

public class SellerAmountDTO {
    private String sellerName;
    private Double totalAmount;

    public SellerAmountDTO() {
    }

    public SellerAmountDTO(String sellerName, Double totalAmount) {
        this.sellerName = sellerName;
        this.totalAmount = totalAmount;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
