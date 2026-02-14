package com.darlan.productservice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name="product")
public class Product {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    protected Product() {        
    }

    public Product(String name, BigDecimal price) {        
        this.name = name;
        this.price = price;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public BigDecimal getPrice() { return price; }

    public void setName(String name) { this.name = name; }
    public void setPrice(BigDecimal price) { this.price = price;}
}