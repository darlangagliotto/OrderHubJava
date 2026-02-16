package com.darlan.orderservice.entity;

import com.darlan.orderservice.entity.OrderItem;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name="orders")
public class Order {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "client_id", nullable = false)    
    private UUID clientId;

    @Column(nullable = false, length = 120)
    private String address;

    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;    

    protected Order(){        
    }

    public Order(UUID clientId, String address) {
        this.clientId = clientId;
        this.address = address;
        this.totalAmount = BigDecimal.ZERO;
        this.createdAt = LocalDate.now();
    }

    public UUID getId() { return id; }
    public UUID getClientId() { return clientId; }
    public String getAddress() { return address; }
    public BigDecimal getTotalAmount() { return totalAmount; }
    public LocalDate getCreatedAt() { return createdAt; }
    
    public void setAddress(String addres) { this.address = addres; }


    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(OrderItem item) {
        if (item == null) {
            throw new IllegalArgumentException("O item não pode ser null");
        }
        items.add(item);
        item.setOrder(this);
        recalculateTotal();
    }

    public void removeItem(OrderItem item) {
        items.remove(item);
        item.setOrder(null);
        recalculateTotal();
    }

    private void recalculateTotal() {
        this.totalAmount = items.stream()
        .map(OrderItem::getLineTotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
