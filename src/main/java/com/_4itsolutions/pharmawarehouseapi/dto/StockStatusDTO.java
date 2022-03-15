package com._4itsolutions.pharmawarehouseapi.dto;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class StockStatusDTO {
    @Valid
    @NotNull
    private ProductDTO product;

    @Min(0)
    @NotNull
    private Integer quantity;

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public StockStatusDTO() {
    }

    public StockStatusDTO(ProductDTO product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "StockStatus{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
