package com.darlan.clientservice.mapper;

import com.darlan.clientservice.dto.ClientRequestDto;
import com.darlan.clientservice.dto.ClientResponseDto;
import com.darlan.clientservice.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mapping(target = "id", ignore = true)    
    Client toEntity(ClientRequestDto request);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromRequest(ClientRequestDto request, @MappingTarget Client client);

    ClientResponseDto toDto(Client client);
}
