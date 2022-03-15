package com._4itsolutions.pharmawarehouseapi.service;

import com._4itsolutions.pharmawarehouseapi.dto.CustomerDTO;
import com._4itsolutions.pharmawarehouseapi.persistence.dao.CustomerDAO;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;
    private final ModelMapper modelMapper;

    public CustomerService(CustomerDAO customerDAO, ModelMapper modelMapper) {
        this.customerDAO = customerDAO;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer = customerDAO.save(customer);
        return modelMapper.map(customer, CustomerDTO.class);
    }

    public List<CustomerDTO> getAll() {
        return customerDAO.findAll()
                .stream()
                .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                .collect(Collectors.toList());
    }
}
