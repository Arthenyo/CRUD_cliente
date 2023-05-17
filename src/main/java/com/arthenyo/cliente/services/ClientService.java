package com.arthenyo.cliente.services;

import com.arthenyo.cliente.entities.Client;
import com.arthenyo.cliente.entities.DTO.ClientDTO;
import com.arthenyo.cliente.repositories.ClientRepositorys;
import com.arthenyo.cliente.services.exceptions.ResouceNotFoundExcption;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepositorys clientRepositorys;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> clients = clientRepositorys.findAll(pageable);
        return clients.map(x -> new ClientDTO(x));
    }
    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
        Client client = clientRepositorys.findById(id)
                .orElseThrow(() -> new ResouceNotFoundExcption("Objeto não encontrado"));
        return new ClientDTO(client);
    }
    @Transactional
    public ClientDTO insert(ClientDTO dto){
        Client entity = new Client();
        copyDtoToEntity(dto, entity);
        entity = clientRepositorys.save(entity);
        return new ClientDTO(entity);
    }
    @Transactional
    public ClientDTO update(Long id, ClientDTO dto){
        try {
            Client entity = clientRepositorys.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = clientRepositorys.save(entity);
            return new ClientDTO(entity);
        }catch (EntityNotFoundException e){
            throw new ResouceNotFoundExcption("Objeto não encontrado");
        }

    }
    @Transactional
    public void delete(Long id){
        try {
            clientRepositorys.deleteById(id);
        }catch (EmptyResultDataAccessException e) {
            throw new ResouceNotFoundExcption("Recurso não encontrado");
        }

    }

    private void copyDtoToEntity(ClientDTO dto, Client entity) {
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
        entity.setChildren(dto.getChildren());
    }

}
