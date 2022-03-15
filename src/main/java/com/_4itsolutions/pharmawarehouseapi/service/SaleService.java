package com._4itsolutions.pharmawarehouseapi.service;

import com._4itsolutions.pharmawarehouseapi.dto.SaleRegistrationDTO;
import com._4itsolutions.pharmawarehouseapi.dto.SaleSummaryDTO;
import com._4itsolutions.pharmawarehouseapi.exception.SaleRegistrationException;
import com._4itsolutions.pharmawarehouseapi.persistence.dao.CustomerDAO;
import com._4itsolutions.pharmawarehouseapi.persistence.dao.ProductDAO;
import com._4itsolutions.pharmawarehouseapi.persistence.dao.SaleDAO;
import com._4itsolutions.pharmawarehouseapi.persistence.dao.StockDAO;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Customer;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Product;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Sale;
import com._4itsolutions.pharmawarehouseapi.persistence.entity.Stock;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class SaleService {
    private final ProductDAO productDAO;
    private final CustomerDAO customerDAO;
    private final StockDAO stockDAO;
    private final SaleDAO saleDAO;
    private final ModelMapper modelMapper;

    public SaleService(
            ProductDAO productDAO,
            CustomerDAO customerDAO,
            StockDAO stockDAO,
            SaleDAO saleDAO,
            ModelMapper modelMapper
    ) {
        this.productDAO = productDAO;
        this.customerDAO = customerDAO;
        this.stockDAO = stockDAO;
        this.saleDAO = saleDAO;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public SaleSummaryDTO register(SaleRegistrationDTO saleRegistrationDTO) {
        Integer quantity = saleRegistrationDTO.getQuantity();
        Product product = productDAO.getByBarCode(saleRegistrationDTO.getProductBarCode());
        Customer customer = customerDAO.getByNickName(saleRegistrationDTO.getCustomerNickName());
        Stock stock = stockDAO.getByProduct(product);

        preValidateRegistration(quantity, product, customer, stock, saleRegistrationDTO);

        Sale sale = saleDAO.save(new Sale(product, customer, quantity));
        if (stock.getQuantity().equals(quantity)) {
            stockDAO.delete(stock);
        } else {
            stock.setQuantity(stock.getQuantity() - quantity);
            stockDAO.save(stock);
        }

        return SaleSummaryDTO.map(sale, modelMapper);
    }

    public List<SaleSummaryDTO> getAll(String customerNickName, Integer productBarCode) {
        Stream<Sale> stream = saleDAO.findAll().stream();

        if (customerNickName != null) {
            stream = stream.filter(it -> it.getCustomer().getNickName().equals(customerNickName));
        }
        if (productBarCode != null) {
            stream = stream.filter(it -> it.getProduct().getBarCode().equals(productBarCode));
        }

        return stream
                .map(sale -> SaleSummaryDTO.map(sale, modelMapper))
                .collect(Collectors.toList());
    }

    private void preValidateRegistration(
            Integer quantity,
            Product product,
            Customer customer,
            Stock stock,
            SaleRegistrationDTO saleRegistrationDTO
    ) {
        // todo move messages to application.properties
        if (quantity == null || quantity <= 0) {
            throw new SaleRegistrationException(saleRegistrationDTO,
                    "Quantity must be greater than 0.");
        }

        if (product == null) {
            throw new SaleRegistrationException(saleRegistrationDTO,
                    "Product does not exist.");
        }

        if (customer == null) {
            throw new SaleRegistrationException(saleRegistrationDTO,
                    "Customer does not exist.");
        }

        if (stock == null) {
            throw new SaleRegistrationException(saleRegistrationDTO,
                    "Product not in stock.");
        }

        Integer storedQuantity = stock.getQuantity();
        if (storedQuantity < quantity) {
            throw new SaleRegistrationException(saleRegistrationDTO,
                    "Insufficient quantity of goods in stock. Left: " + storedQuantity);
        }
    }
}
