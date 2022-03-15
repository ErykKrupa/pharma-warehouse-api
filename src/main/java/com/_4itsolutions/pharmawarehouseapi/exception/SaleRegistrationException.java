package com._4itsolutions.pharmawarehouseapi.exception;

import com._4itsolutions.pharmawarehouseapi.dto.SaleRegistrationDTO;

public class SaleRegistrationException extends RequestProcessingException {
    public SaleRegistrationException(SaleRegistrationDTO saleRegistrationDTO) {
        this(saleRegistrationDTO, "");
    }

    public SaleRegistrationException(SaleRegistrationDTO saleRegistrationDTO, String message) {
        super("Cannot register sale: " + saleRegistrationDTO + ". " + message);
    }
}
