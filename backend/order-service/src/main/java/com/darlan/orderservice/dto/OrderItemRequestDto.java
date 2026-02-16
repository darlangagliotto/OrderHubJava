package com.darlan.orderservice.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;

public record OrderItemRequestDto (
    @Schema(description = "Id do produto")
    @NotNull(message = "O produto é obrigatório")
    UUID productId,

    @Schema(description = "Quantidade do produto")
    @NotNull(message = "A quantidade é obrigatório")
    @Min(value =1, message = "O pedido deve ter pelo menos um item")
    int quantity
){
}
