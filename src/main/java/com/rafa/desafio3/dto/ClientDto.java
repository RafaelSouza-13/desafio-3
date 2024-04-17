package com.rafa.desafio3.dto;

import com.rafa.desafio3.entities.Client;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClientDto {
    private Long id;
    @NotBlank(message = "O campo nome deve ser preenchido. Não pode ser vazio")
    private String name;
    private String cpf;
    @Positive(message = "O campo renda não pode ser negativo")
    private Double income;
    @PastOrPresent(message = "Data de nascimento não pode ser no futuro")
    private LocalDate birthDate;
    private Integer children;

    public ClientDto(Long id, String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDto(Client client){
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.income = client.getIncome();
        this.birthDate = client.getBirthDate();
        this.children = client.getChildren();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getChildren() {
        return children;
    }
}
