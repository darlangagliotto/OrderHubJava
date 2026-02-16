package com.darlan.orderservice.mapper;

import com.darlan.orderservice.dto.OrderItemResponseDto;
import com.darlan.orderservice.dto.OrderResponseDto;
import com.darlan.orderservice.entity.Order;
import com.darlan.orderservice.entity.OrderItem;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderMapper {

    public OrderResponseDto toDto(Order order) {
        List<OrderItemResponseDto> items = order.getItems().stream()
                .map(this::toItemDto)
                .toList();

        return new OrderResponseDto(
            order.getId(),
                order.getClientId(),
                order.getAddress(),
                order.getTotalAmount(),
                order.getCreatedAt(),
                items
        );
    }

    private OrderItemResponseDto toItemDto(OrderItem item) {
        return new OrderItemResponseDto(
                item.getId(),
                item.getProductId(),
                item.getQuantity(),
                item.getUnitPrice(),
                item.getLineTotal()
        );
    }
}
