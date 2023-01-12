package com.pessoas_api.pessoas_api.controllers.dto;

import com.pessoas_api.pessoas_api.core.entities.Endereco;

public class EnderecoDTO {
    private Long id;
    private String logradouro;
    private String cep;
    private Long numero;
    private String cidade;
    private String uf;

    public EnderecoDTO(Endereco endereco) {
        this.id = endereco.getId();
        this.logradouro = endereco.getLogradouro();
        this.cep = endereco.getCep();
        this.numero = endereco.getNumero();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getUf();
    }

    public Long getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getCep() {
        return cep;
    }

    public Long getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }
}
