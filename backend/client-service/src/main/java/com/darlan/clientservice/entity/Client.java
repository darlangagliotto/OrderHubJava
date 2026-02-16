package com.darlan.clientservice.entity;

import com.darlan.clientservice.exception.BusinessException;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="clients")
public class Client{

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, length = 120)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDay;

    @Column(nullable=false, unique=true, length=11)
    private String cpf;

    @Column(nullable = false, length = 120)
    private String address;

    @Column(nullable = false)
    private boolean active;

    protected Client(){
    }

    public Client(String name, LocalDate birthDay, String cpf, String address, boolean active) {
        this.name = name;
        this.birthDay = birthDay;
        this.cpf = cpf;
        this.address = address;
        this.active = true;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getCpf() { return cpf; }
    public LocalDate getBirthDay() { return birthDay; }
    public String getAddress() { return address; }
    public Boolean isActive() { return active; }

    public void changeName(String name) { this.name = name; }
    public void changeCpf(String cpf) { this.cpf = cpf; }
    public void changeBirthDay(LocalDate birthDay) { this.birthDay = birthDay; }
    public void changeAddress(String address) { this.address = address; }

    public void deactivate() { 
        if (!this.active) {
            throw new BusinessException("O cliente de id: %s já está inativo".formatted(id));
        }
        this.active = false; 
    }
}
