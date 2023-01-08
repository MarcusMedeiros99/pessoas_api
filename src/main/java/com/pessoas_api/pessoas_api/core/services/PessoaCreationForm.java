package com.pessoas_api.pessoas_api.core.services;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pessoas_api.pessoas_api.core.entities.Endereco;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class PessoaCreationForm {
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate dataNascimento;
    @NotNull
    @Size(min = 3)
    private String nome;
    @NotNull
    @Size(min = 3)
    private String logradouro;
    @NotNull
    @Size(max = 8, min = 8, message = "CEP deve possuir 8 caracteres")
    private String cep;
    private Long numero;
    private String cidade;
    private String uf;

    public String getNome() {
        return nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
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

    public Pessoa convert() {
        Pessoa pessoa = new Pessoa(
                    this.getNome(),
                    this.getDataNascimento()
            );
        Endereco endereco = new Endereco(
                this.logradouro,
                this.cep,
                this.numero,
                this.cidade,
                this.uf,
                pessoa
            );
        pessoa.addEndereco(endereco);
        return pessoa;
    }
}
