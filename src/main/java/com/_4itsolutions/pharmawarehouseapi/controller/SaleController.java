package com._4itsolutions.pharmawarehouseapi.controller;

import com._4itsolutions.pharmawarehouseapi.dto.SaleRegistrationDTO;
import com._4itsolutions.pharmawarehouseapi.dto.SaleSummaryDTO;
import com._4itsolutions.pharmawarehouseapi.exception.SaleRegistrationException;
import com._4itsolutions.pharmawarehouseapi.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "${url}/sales", produces = "application/json")
public class SaleController {
    private SaleService saleService;

    @Autowired
    void setSaleService(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaleSummaryDTO registerSale(
            @RequestBody SaleRegistrationDTO saleRegistrationDTO
    ) {
        try {
            return saleService.register(saleRegistrationDTO);
        } catch (DataIntegrityViolationException ex) {
            throw new SaleRegistrationException(saleRegistrationDTO);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SaleSummaryDTO> listSales(
            @RequestParam(required = false) String customerNickName,
            @RequestParam(required = false) Integer productBarCode
    ) {
        return saleService.getAll(customerNickName, productBarCode);
    }
}
