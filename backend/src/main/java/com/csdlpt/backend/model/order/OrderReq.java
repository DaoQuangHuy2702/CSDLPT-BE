package com.csdlpt.backend.model.order;

public class OrderReq {
    private int customerId;

    private int productDetailId;

    private String status;

    public int getCustomerId() { return customerId; }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getProductDetailId() { return productDetailId; }

    public void setProductDetailId(int productDetailId) {
        this.productDetailId = productDetailId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
