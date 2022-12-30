package com.bitpuzzle.invoicegenerator.mappers;


import com.bitpuzzle.invoicegenerator.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = "spring")
public interface ClientMapper {

        com.bitpuzzle.invoicegenerator.dto.ClientDto mapToClientDto(Client client);
}
