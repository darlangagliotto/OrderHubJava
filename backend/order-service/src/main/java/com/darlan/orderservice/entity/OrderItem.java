package com.darlan.orderservice.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

import com.darlan.orderservice.exception.BusinessException;

@Entity
@Table(name="order_items")
public class OrderItem{

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    
    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(nullable=false)
    private int quantity;

    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "line_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal lineTotal;

    protected OrderItem(){        
    }

    public OrderItem(UUID productId, int quantity, BigDecimal unitPrice) {
        if (productId == null)
            throw new IllegalArgumentException("ProductId não pode ser null");

        if (quantity <= 0)
            throw new IllegalArgumentException("Quantidade deve ser maior que zero");

        if (unitPrice == null || unitPrice.compareTo(BigDecimal.ZERO) <= 0)
            throw new BusinessException("Valor unitário para o produto %s deve ser maior do que zero".formatted(productId));

        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.lineTotal = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }

    void setOrder(Order order) {
        this.order = order;
    }
    
    public UUID getId() { return id; }
    public UUID getProductId() { return productId; }    
    public int getQuantity() { return quantity; }
    public BigDecimal getUnitPrice() { return unitPrice; }
    public BigDecimal getLineTotal() { return lineTotal; }
}