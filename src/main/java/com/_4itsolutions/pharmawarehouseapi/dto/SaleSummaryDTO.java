package com._4itsolutions.pharmawarehouseapi.dto;

import com._4itsolutions.pharmawarehouseapi.persistence.entity.Sale;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class SaleSummaryDTO {
    @Valid
    private ProductDTO product;

    @Valid
    private CustomerDTO customer;

    @Min(1)
    @NotNull
    private Integer quantity;

    public SaleSummaryDTO() {
    }

    public SaleSummaryDTO(ProductDTO product, CustomerDTO customer, Integer quantity) {
        this.product = product;
        this.customer = customer;
        this.quantity = quantity;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "SaleSummary{" +
                "product=" + product +
                ", customer=" + customer +
                ", quantity=" + quantity +
                '}';
    }

    public static SaleSummaryDTO map(Sale sale, ModelMapper modelMapper) {
        return new SaleSummaryDTO(
                modelMapper.map(sale.getProduct(), ProductDTO.class),
                modelMapper.map(sale.getCustomer(), CustomerDTO.class),
                sale.getQuantity()
        );
    }
}
