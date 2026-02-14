package com.darlan.clientservice.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name="client")
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
    private Boolean isActive;

    protected Client(){
    }

    public Client(String name, LocalDate birthDay, String cpf, String address, Boolean isActive) {
        this.name = name;
        this.birthDay = birthDay;
        this.cpf = cpf;
        this.address = address;
        this.isActive = isActive;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getCpf() { return cpf; }
    public LocalDate getBirthDay() { return birthDay; }
    public String getAddress() { return address; }
    public Boolean getIsActive() { return isActive; }

    public void setName(String name) { this.name = name; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public void setBirthDay(LocalDate birthDay) { this.birthDay = birthDay; }
    public void setAddress(String address) { this.address = address; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
}
