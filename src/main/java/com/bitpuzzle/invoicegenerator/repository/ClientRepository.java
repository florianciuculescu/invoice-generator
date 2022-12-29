package com.bitpuzzle.invoicegenerator.repository;

import com.bitpuzzle.invoicegenerator.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
