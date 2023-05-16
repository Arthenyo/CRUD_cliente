package com.arthenyo.cliente.repositories;

import com.arthenyo.cliente.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepositorys extends JpaRepository<Client, Long> {
}
