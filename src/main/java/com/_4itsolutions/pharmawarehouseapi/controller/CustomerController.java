package com._4itsolutions.pharmawarehouseapi.controller;

import com._4itsolutions.pharmawarehouseapi.dto.CustomerDTO;
import com._4itsolutions.pharmawarehouseapi.exception.RequestProcessingException;
import com._4itsolutions.pharmawarehouseapi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/${url}/customers", produces = "application/json")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO addCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        try {
            return customerService.save(customerDTO);
        } catch (DataIntegrityViolationException ex) {
            throw new RequestProcessingException("Cannot save: " + customerDTO + ".");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> listCustomers() {
        return customerService.getAll();
    }
}
