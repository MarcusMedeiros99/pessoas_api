package com.pessoas_api.pessoas_api.controllers.forms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pessoas_api.pessoas_api.core.entities.Endereco;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PessoaCreationForm {
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
    @NotNull
    @Size(min = 3)
    private String nome;
    @NotNull
    @Size(min = 3)
    private String logradouro;
    @NotNull
    @Pattern(regexp = "[\\d]{8}")
    private String cep;
    @Min(0)
    private Long numero;
    @NotNull
    @Size(min = 2)
    private String cidade;
    @NotNull
    @Size(min = 2, max = 2)
    private String uf;

    public PessoaCreationForm() {

    }

    PessoaCreationForm(LocalDate dataNascimento, String nome, String logradouro, String cep, Long numero, String cidade, String uf) {
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.uf = uf;
    }

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
