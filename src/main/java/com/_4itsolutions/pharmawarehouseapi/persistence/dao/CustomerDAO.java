package com._4itsolutions.pharmawarehouseapi.persistence.dao;

import com._4itsolutions.pharmawarehouseapi.persistence.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerDAO extends JpaRepository<Customer, Long> {
    Customer getByNickName(String nickName);
}
