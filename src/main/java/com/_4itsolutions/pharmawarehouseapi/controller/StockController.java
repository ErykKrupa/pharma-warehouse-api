package com._4itsolutions.pharmawarehouseapi.controller;

import com._4itsolutions.pharmawarehouseapi.dto.StockDeliveryDTO;
import com._4itsolutions.pharmawarehouseapi.dto.StockStatusDTO;
import com._4itsolutions.pharmawarehouseapi.exception.StockStoreException;
import com._4itsolutions.pharmawarehouseapi.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/${url}/stocks", produces = "application/json")
public class StockController {
    private StockService stockService;

    @Autowired
    void setStockService(StockService stockService) {
        this.stockService = stockService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockStatusDTO storeStock(@RequestBody @Valid StockDeliveryDTO stockDeliveryDTO) {
        try {
            return stockService.store(stockDeliveryDTO);
        } catch (DataIntegrityViolationException ex) {
            throw new StockStoreException(stockDeliveryDTO);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StockStatusDTO> listStocks() {
        return stockService.getAll();
    }
}
