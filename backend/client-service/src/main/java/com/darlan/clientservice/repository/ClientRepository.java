package com.darlan.clientservice.repository;

import com.darlan.clientservice.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findByActiveFalse();
    List<Client> findByNameContainingIgnoreCase(String name);    
}
