package com.darlan.clientservice.controller;

import com.darlan.clientservice.dto.ClientRequestDto;
import com.darlan.clientservice.dto.ClientResponseDto;
import com.darlan.clientservice.service.ClientService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.UUID;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/client")
@Tag(name = "Cliente", description = "Operações de CRUD de clientes")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientResponseDto> getAll() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ClientResponseDto geById(@PathVariable UUID id) {
        return clientService.getById(id);
    }
    
    @GetMapping("/inactive")
    public List<ClientResponseDto> getAllInactiveClients() {
        return clientService.getAllInactiveClients();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientResponseDto create(@Valid @RequestBody ClientRequestDto request) {
        return clientService.create(request);
    }
    
    @PutMapping("{id}")    
    public ClientResponseDto update(@PathVariable UUID id, @Valid @RequestBody ClientRequestDto request) {
        return clientService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        clientService.delete(id);
    }    
}
