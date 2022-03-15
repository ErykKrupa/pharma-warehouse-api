package com._4itsolutions.pharmawarehouseapi.service;

import com._4itsolutions.pharmawarehouseapi.dto.ProductDTO;
import com._4itsolutions.pharmawarehouseapi.persistence.dao.ProductDAO;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductDAO productDAO;
    private final ModelMapper modelMapper;

    public ProductService(ProductDAO productDAO, ModelMapper modelMapper) {
        this.productDAO = productDAO;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public ProductDTO save(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        product = productDAO.save(product);
        return modelMapper.map(product, ProductDTO.class);
    }

    public List<ProductDTO> getAll() {
        return productDAO.findAll()
                .stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
