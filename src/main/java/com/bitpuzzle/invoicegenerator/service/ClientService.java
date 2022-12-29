package com.bitpuzzle.invoicegenerator.service;

import com.bitpuzzle.invoicegenerator.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    Client findClientById(Integer id);

    List<Client> fetchClientsList();
}
