package com.darlan.orderservice.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darlan.orderservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, UUID>{
    
}
