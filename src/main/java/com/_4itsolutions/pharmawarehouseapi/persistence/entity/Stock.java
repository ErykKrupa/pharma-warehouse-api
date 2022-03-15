package com._4itsolutions.pharmawarehouseapi.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table
public class Stock {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "productId", nullable = false)
    private Product product;

    @Min(0)
    @Column(nullable = false)
    private Integer quantity;

    public Stock() {
    }

    public Stock(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}