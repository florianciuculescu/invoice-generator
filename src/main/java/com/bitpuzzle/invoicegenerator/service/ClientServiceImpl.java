package com.bitpuzzle.invoicegenerator.service;

import com.bitpuzzle.invoicegenerator.model.Client;
import com.bitpuzzle.invoicegenerator.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    public final ClientRepository clientRepository;

    @Override
    public Client findClientById(Integer id) {
        //TODO: refactor this with custom exception
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No client found !"));
    }

    @Override
    public List<Client> fetchClientsList() {
        return clientRepository.findAll();
    }
}
