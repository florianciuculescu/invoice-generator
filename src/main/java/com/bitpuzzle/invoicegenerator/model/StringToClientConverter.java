package com.bitpuzzle.invoicegenerator.model;

import com.bitpuzzle.invoicegenerator.dto.ClientDto;
import com.bitpuzzle.invoicegenerator.mappers.ClientMapper;
import com.bitpuzzle.invoicegenerator.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToClientConverter implements Converter<String, ClientDto> {

    @Autowired
    ClientService clientService;

    @Autowired
    ClientMapper clientMapper;

    @Override
    public ClientDto convert(String source) {

        Client client = clientService.findClientById(Integer.valueOf(source));

        return clientMapper.mapToClientDto(client);
    }
}
