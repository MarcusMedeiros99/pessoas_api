package com.pessoas_api.pessoas_api.controllers.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;

import java.time.LocalDate;

public class PessoaDTO {
    private Long id;
    private String nome;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    private EnderecoDTO enderecoPrincipal;

    @Deprecated
    PessoaDTO() {};

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.dataNascimento = pessoa.getDataNascimento();
        this.enderecoPrincipal = new EnderecoDTO(pessoa.getEnderecoPrincipal());
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public EnderecoDTO getEnderecoPrincipal() {
        return enderecoPrincipal;
    }
}
