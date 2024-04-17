package com.rafa.desafio3.services;

import com.rafa.desafio3.dto.ClientDto;
import com.rafa.desafio3.entities.Client;
import com.rafa.desafio3.repositories.ClientRepository;
import com.rafa.desafio3.services.exceptions.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {
    @Autowired
    ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDto findById(Long id){
        Client client = repository.findById(id).orElseThrow(()-> new ClientNotFoundException("Cliente com o id solicitado não foi encontrado"));
        return new ClientDto(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable){
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDto(x));
    }

    @Transactional
    public ClientDto insert(ClientDto dto){
        Client client = new Client();
        dtoToClient(dto, client);
        client = repository.save(client);
        return new ClientDto(client);
    }

    private void dtoToClient(ClientDto dto, Client client){
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setIncome(dto.getIncome());
        client.setChildren(dto.getChildren());
        client.setBirthDate(dto.getBirthDate());
    }
}
