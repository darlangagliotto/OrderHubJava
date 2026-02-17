package com.darlan.productservice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

import com.darlan.productservice.exception.BusinessException;

@Entity
@Table(name="products")
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(nullable = false)
    private int stockQuantity;

    protected Product() {        
    }

    public Product(String name, BigDecimal price, int stockQuantity) {
        if (stockQuantity < 0) {
            throw new BusinessException("Estoque não pode ser negativo");
        }

        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("Preço deve ser maior que zero");
        }

        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            throw new BusinessException("Quantidade deve ser maior que zero");
        }

        if (this.stockQuantity < quantity) {
            throw new BusinessException("Estoque insuficiente");
        }

        this.stockQuantity -= quantity;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    public void changeName(String name) { this.name = name; }
    public void changePrice(BigDecimal price) { this.price = price;}
}