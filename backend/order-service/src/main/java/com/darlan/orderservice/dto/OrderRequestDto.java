package com.darlan.orderservice.dto;

import java.util.List;
import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.Valid;

public record OrderRequestDto(

    @Schema(description = "Id do cliente")
    @NotNull(message = "O cliente é obrigatório")
    UUID clientId,
    
    @Schema(description = "Endereço de entrega")
    @NotBlank(message = "O endereço de entrega é obrigatório")
    @Size(max= 120, message="O endereço de entrega deve ter no máximo 120 caracteres")
    String address,

    @Schema(description = "Os itens do pedido")
    @NotNull(message = "O itens do pedido são obrigatórios")
    @Size(min =1, message = "O pedido deve ter pelo menos um item")
    List<@NotNull @Valid OrderItemRequestDto> items
) {    
}


    

