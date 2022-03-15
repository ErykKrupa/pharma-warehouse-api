package com._4itsolutions.pharmawarehouseapi.controller;

import com._4itsolutions.pharmawarehouseapi.dto.ProductDTO;
import com._4itsolutions.pharmawarehouseapi.exception.RequestProcessingException;
import com._4itsolutions.pharmawarehouseapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/${url}/products", produces = "application/json")
public class ProductController {
    private ProductService productService;

    @Autowired
    void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO addProduct(@RequestBody @Valid ProductDTO productDTO) {
        try {
            return productService.save(productDTO);
        } catch (DataIntegrityViolationException ex) {
            throw new RequestProcessingException("Cannot save: " + productDTO + ".");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> listProducts() {
        return productService.getAll();
    }
}
