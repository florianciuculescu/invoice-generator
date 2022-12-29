package com.bitpuzzle.invoicegenerator.model;

import com.bitpuzzle.invoicegenerator.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToClientConverter implements Converter<String, Client> {

    @Autowired
    ClientService clientService;

    @Override
    public Client convert(String source) {

        return clientService.findClientById(Integer.valueOf(source));
    }
}
