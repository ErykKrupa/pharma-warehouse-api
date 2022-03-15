package com._4itsolutions.pharmawarehouseapi.persistence.dao;

import com._4itsolutions.pharmawarehouseapi.persistence.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleDAO extends JpaRepository<Sale, Long> {
    // all needed methods already implemented
}