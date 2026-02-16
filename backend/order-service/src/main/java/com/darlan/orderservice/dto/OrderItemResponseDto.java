package com.darlan.orderservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemResponseDto (
    UUID id,
    UUID productId,
    int quantity,
    BigDecimal unitPrice,
    BigDecimal lineTotal
){    
}
