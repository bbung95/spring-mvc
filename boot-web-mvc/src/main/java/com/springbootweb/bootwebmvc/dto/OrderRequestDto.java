package com.springbootweb.bootwebmvc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties("payPrivateKey")
public class OrderRequestDto {

    private int productId;
    private int orderCount;
    private int price;
    private String receiverName;
    private String receiverAddress;
    private String payPrivateKey;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getPayPrivateKey() {
        return payPrivateKey;
    }

    public void setPayPrivateKey(String payPrivateKey) {
        this.payPrivateKey = payPrivateKey;
    }
}
