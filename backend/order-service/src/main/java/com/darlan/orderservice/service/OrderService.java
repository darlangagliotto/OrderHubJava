package com.darlan.orderservice.service;

import com.darlan.orderservice.application.port.ProductCatalogPort;
import com.darlan.orderservice.dto.OrderItemRequestDto;
import com.darlan.orderservice.dto.OrderRequestDto;
import com.darlan.orderservice.dto.OrderResponseDto;
import com.darlan.orderservice.dto.ProductSnapshotDto;
import com.darlan.orderservice.entity.Order;
import com.darlan.orderservice.entity.OrderItem;
import com.darlan.orderservice.exception.BusinessException;
import com.darlan.orderservice.mapper.OrderMapper;
import com.darlan.orderservice.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductCatalogPort productCatalogPort;
    
    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper, ProductCatalogPort productCatalogPort) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.productCatalogPort = productCatalogPort;
    }

    @Transactional(readOnly = true)
        public List<OrderResponseDto> getAllOrders() {
            return orderRepository.findAll()
                .stream()
                .map(orderMapper::toDto)
                .toList();
        }

    @Transactional
    public OrderResponseDto create(OrderRequestDto request) {
        Order order = new Order(request.clientId(), request.address());

        for (OrderItemRequestDto itemRequest : request.items()) {
            ProductSnapshotDto product = productCatalogPort.getProduct((itemRequest.productId()));

            if (product.stockQuantity() < itemRequest.quantity()) {
                throw new BusinessException("Estoque insuficiente para o produto: " + itemRequest.productId());
            }

            OrderItem item = new OrderItem(
                itemRequest.productId(),
                itemRequest.quantity(),
                product.unitPrice()
            );
            order.addItem(item);
        }

        Order saved = orderRepository.save(order);

        for (OrderItem item : saved.getItems()) {
            productCatalogPort.decreaseStock(item.getProductId(), item.getQuantity());
        }

        return orderMapper.toDto(saved);
    }

    @Transactional
    public OrderResponseDto update(UUID id, OrderRequestDto request) {
        Order order = findById(id);
        order.setAddress(request.address());
        Order updated = orderRepository.save(order);
        return orderMapper.toDto(updated);
    }

    @Transactional
    public void delete(UUID id) {
        // TO DO
    }

    private Order findById(UUID id) {
        Order order = orderRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Pedido não encontado para o id: %s".formatted(id)));
        return order;
    }
}
