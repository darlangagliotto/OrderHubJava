package com.darlan.orderservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductSnapshotDto (
    UUID id, 
    BigDecimal unitPrice, 
    int stockQuantity
){    
}
