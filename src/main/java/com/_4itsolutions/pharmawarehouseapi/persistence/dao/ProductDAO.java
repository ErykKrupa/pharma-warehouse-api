package com._4itsolutions.pharmawarehouseapi.persistence.dao;

import com._4itsolutions.pharmawarehouseapi.persistence.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDAO extends JpaRepository<Product, Long> {
    Product getByBarCode(Integer barCode);
}
