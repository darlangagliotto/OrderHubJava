package com.darlan.productservice.exception;

import java.util.UUID;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(UUID id) {
        super("Produto não encontrado para o id: %s".formatted(id));
    }
}