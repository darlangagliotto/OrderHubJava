package com.darlan.orderservice.application.port;

import java.util.UUID;

import com.darlan.orderservice.dto.ProductSnapshotDto;

public interface ProductCatalogPort {
    
    ProductSnapshotDto getProduct(UUID productId);
    void decreaseStock(UUID productId, int quantity);    
}
