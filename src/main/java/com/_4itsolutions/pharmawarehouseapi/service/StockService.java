package com._4itsolutions.pharmawarehouseapi.service;

import com._4itsolutions.pharmawarehouseapi.dto.ProductDTO;
import com._4itsolutions.pharmawarehouseapi.dto.SaleRegistrationDTO;
import com._4itsolutions.pharmawarehouseapi.dto.StockDeliveryDTO;
import com._4itsolutions.pharmawarehouseapi.dto.StockStatusDTO;
import com._4itsolutions.pharmawarehouseapi.exception.SaleRegistrationException;
import com._4itsolutions.pharmawarehouseapi.exception.StockStoreException;
import com._4itsolutions.pharmawarehouseapi.persistence.dao.ProductDAO;
import com._4itsolutions.pharmawarehouseapi.persistence.dao.StockDAO;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Customer;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Product;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Stock;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StockService {
    private final StockDAO stockDAO;
    private final ProductDAO productDAO;
    private final ModelMapper modelMapper;

    public StockService(StockDAO stockDAO, ProductDAO productDAO, ModelMapper modelMapper) {
        this.stockDAO = stockDAO;
        this.productDAO = productDAO;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public StockStatusDTO store(StockDeliveryDTO stockDeliveryDTO) {
        Product product = productDAO.getByBarCode(stockDeliveryDTO.getProductBarCode());
        Integer quantityToStore = stockDeliveryDTO.getQuantityToStore();

        preValidateStore(quantityToStore, product, stockDeliveryDTO);

        Stock stock = stockDAO.getByProduct(product);
        if (stock == null) {
            stock = new Stock(product, quantityToStore);
        } else {
            stock.setQuantity(stock.getQuantity() + quantityToStore);
        }
        stock = stockDAO.save(stock);

        return new StockStatusDTO(
                modelMapper.map(stock.getProduct(), ProductDTO.class),
                stock.getQuantity()
        );
    }

    public List<StockStatusDTO> getAll() {
        return stockDAO.findAll()
                .stream()
                .map(stock -> modelMapper.map(stock, StockStatusDTO.class))
                .collect(Collectors.toList());
    }

    private void preValidateStore(
            Integer quantityToStore,
            Product product,
            StockDeliveryDTO stockDeliveryDTO
    ) {
        // todo move messages to application.properties
        if (quantityToStore == null || quantityToStore <= 0) {
            throw new StockStoreException(stockDeliveryDTO,
                    "Quantity must be greater than 0.");
        }

        if (product == null) {
            throw new StockStoreException(stockDeliveryDTO,
                    "Product does not exist.");
        }

    }
}
