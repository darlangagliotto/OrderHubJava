package com.darlan.orderservice.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import java.util.List;

public record OrderResponseDto (
    UUID id,
    UUID clientId,
    String address,    
    BigDecimal totalAmount,
    LocalDate createdAt,
    List<OrderItemResponseDto> items
){    
}
