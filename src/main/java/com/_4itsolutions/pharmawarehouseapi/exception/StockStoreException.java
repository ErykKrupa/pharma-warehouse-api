package com._4itsolutions.pharmawarehouseapi.exception;

import com._4itsolutions.pharmawarehouseapi.dto.StockDeliveryDTO;

public class StockStoreException extends RequestProcessingException {
    public StockStoreException(StockDeliveryDTO stockDeliveryDTO) {
        this(stockDeliveryDTO, "");
    }

    public StockStoreException(StockDeliveryDTO stockDeliveryDTO, String message) {
        super("Cannot store: " + stockDeliveryDTO + ". " + message);
    }
}
