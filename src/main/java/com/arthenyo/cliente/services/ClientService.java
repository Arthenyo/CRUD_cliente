package com.arthenyo.cliente.services;

import com.arthenyo.cliente.entities.Client;
import com.arthenyo.cliente.entities.DTO.ClientDTO;
import com.arthenyo.cliente.repositories.ClientRepositorys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired
    private ClientRepositorys clientRepositorys;


    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clients = clientRepositorys.findAll(pageable);
        return clients.map(x -> new ClientDTO(x));
    }

    public ClientDTO findById(Long id){
        Client client = clientRepositorys.findById(id).get();
        return new ClientDTO(client);
    }

}
