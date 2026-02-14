package com.darlan.clientservice.dto;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ClientRequestDto(

    @Schema(description = "Nome completo do cliente", example = "João da Silva")
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 120, message = "O nome deve ter entre 2 e 120 caracteres")
    String name,

    @Schema(description = "CPF do cliente", example = "12345678901")
    @NotBlank(message = "O CPF é obrigatório")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve ter 11 dígitos numéricos")
    String cpf,

    @Schema(description = "Data de nascimento do cliente", example = "1988-06-16")
    @NotNull(message = "A data de nascimento é obrigatória")
    LocalDate birthDay,

    @Schema(description = "Endereço do cliente", example = "Av. Rio Branco, 100 - Blumenau/SC")
    @Size(max = 120, message = "O endereço deve ter no máximo 120 caracteres")
    String address,

    @Schema(description = "Cliente está ativo", example = "true")
    @NotNull(message = "O campo isActive é obrigatório")
    Boolean isActive
) {    
}