package com.rafa.desafio3.controllers;

import com.rafa.desafio3.dto.ClientDto;
import com.rafa.desafio3.services.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/clients")
public class ClientController {
    @Autowired
    ClientService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClientDto> findById(@PathVariable Long id){
        ClientDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<Page<ClientDto>> findAll(Pageable pageable){
        Page<ClientDto> pageClientes = service.findAll(pageable);
        return ResponseEntity.ok(pageClientes);
    }

    @PostMapping
    public ResponseEntity<ClientDto> insert(@Valid @RequestBody ClientDto dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
