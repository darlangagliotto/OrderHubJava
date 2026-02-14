package com.darlan.clientservice.exception;

import java.util.UUID;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(UUID id) {
        super("Cliente não encontrado para o id: %s".formatted(id));
    }
}
