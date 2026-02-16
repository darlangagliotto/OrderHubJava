package com.darlan.orderservice.application.port;

import java.math.BigDecimal;
import java.util.UUID;

public interface ProductCatalogPort {
    
    ProductSnapshot getProduct(UUID productId);
    void decreaseStock(UUID productId, int quantity);
    record ProductSnapshot(UUID id, BigDecimal unitPrice, int stockQuantity) {}
}
