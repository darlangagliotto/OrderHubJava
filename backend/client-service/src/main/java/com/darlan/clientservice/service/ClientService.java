package com.darlan.clientservice.service;

import com.darlan.clientservice.dto.ClientRequestDto;
import com.darlan.clientservice.dto.ClientResponseDto;
import com.darlan.clientservice.entity.Client;
import com.darlan.clientservice.exception.BusinessException;
import com.darlan.clientservice.mapper.ClientMapper;
import com.darlan.clientservice.repository.ClientRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Transactional(readOnly = true)
    public List<ClientResponseDto> getAllClients() {
        return clientRepository.findAll()
        .stream()
        .map(clientMapper::toDto)
        .toList();
    }

    @Transactional(readOnly = true)
    public ClientResponseDto getById(UUID id) {
        Client client = findById(id);
        return clientMapper.toDto(client);
    }

    @Transactional(readOnly = true)
    public List<ClientResponseDto> getAllInactiveClients() {
        return clientRepository.findByIsActiveFalse()
        .stream()
        .map(clientMapper::toDto)
        .toList();
    }

    @Transactional
    public ClientResponseDto create(ClientRequestDto request) {
        Client entity = clientMapper.toEntity(request);
        Client saved = clientRepository.save(entity);
        return clientMapper.toDto(saved);
    }

    @Transactional
    public ClientResponseDto update(UUID id, ClientRequestDto request) {
        Client client = findById(id);
        clientMapper.updateEntityFromRequest(request, client);
        Client updated = clientRepository.save(client);
        return clientMapper.toDto(updated);
    }

    @Transactional
    public void delete(UUID id) {
        Client client = findById(id);
        client.deactivate();
    }

    private Client findById(UUID id) {
        Client client = clientRepository.findById(id)
            .orElseThrow(() -> new BusinessException("Cliente não encontrado para o id: %s".formatted(id)));
        return client;
    }

}
