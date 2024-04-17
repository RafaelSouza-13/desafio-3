package com.rafa.desafio3.services;

import com.rafa.desafio3.dto.ClientDto;
import com.rafa.desafio3.entities.Client;
import com.rafa.desafio3.repositories.ClientRepository;
import com.rafa.desafio3.services.exceptions.ClientNotFoundException;
import com.rafa.desafio3.services.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

    @Transactional
    public ClientDto update(Long id, ClientDto dto) {
        if(!repository.existsById(id)){
            throw new ClientNotFoundException("Cliente não encontrado");
        }
        Client client = repository.getReferenceById(id);
        dtoToClient(dto, client);
        repository.save(client);
        return new ClientDto(client);
    }

    @Transactional
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new ClientNotFoundException("Cliente não encontrado");
        }
        try{
            repository.deleteById(id);
        }catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
