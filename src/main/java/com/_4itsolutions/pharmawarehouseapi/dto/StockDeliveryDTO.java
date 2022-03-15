package com._4itsolutions.pharmawarehouseapi.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class StockDeliveryDTO {
    @Min(0)
    @NotNull
    private Integer productBarCode;

    @Min(1)
    @NotNull
    private Integer quantityToStore;

    public Integer getProductBarCode() {
        return productBarCode;
    }

    public void setProductBarCode(Integer productBarCode) {
        this.productBarCode = productBarCode;
    }

    public Integer getQuantityToStore() {
        return quantityToStore;
    }

    public void setQuantityToStore(Integer quantityToStore) {
        this.quantityToStore = quantityToStore;
    }

    @Override
    public String toString() {
        return "StockDelivery{" +
                "productBarCode='" + productBarCode + '\'' +
                ", quantityToStore=" + quantityToStore +
                '}';
    }
}
