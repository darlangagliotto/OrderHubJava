package com.darlan.clientservice.dto;

import java.time.LocalDate;
import java.util.UUID;

public record ClientResponseDto(
    UUID id,
    String name,
    String cpf,
    LocalDate birthDay,
    String address,
    Boolean isActive
){}
