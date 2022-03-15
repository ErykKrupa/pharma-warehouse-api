package com._4itsolutions.pharmawarehouseapi.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SaleRegistrationDTO {
    @Min(0)
    @NotNull
    private Integer productBarCode;

    @NotBlank
    private String customerNickName;

    @Min(1)
    @NotNull
    private Integer quantity;

    public Integer getProductBarCode() {
        return productBarCode;
    }

    public void setProductBarCode(Integer productBarCode) {
        this.productBarCode = productBarCode;
    }

    public String getCustomerNickName() {
        return customerNickName;
    }

    public void setCustomerNickName(String customerNickName) {
        this.customerNickName = customerNickName;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SaleRegistration{" +
                "productBarCode=" + productBarCode +
                ", customerNickName='" + customerNickName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
