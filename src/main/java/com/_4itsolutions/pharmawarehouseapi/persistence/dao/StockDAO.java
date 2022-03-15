package com._4itsolutions.pharmawarehouseapi.persistence.dao;

import com._4itsolutions.pharmawarehouseapi.persistence.entity.Product;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockDAO extends JpaRepository<Stock, Long> {
    Stock getByProduct(Product product);
}
