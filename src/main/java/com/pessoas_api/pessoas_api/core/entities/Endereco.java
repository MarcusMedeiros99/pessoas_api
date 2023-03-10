package com.pessoas_api.pessoas_api.core.entities;

import jakarta.persistence.*;

@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    @Column(nullable = false, length = 8)
    private String cep;
    private Long numero;
    @Column(nullable = false)
    private String cidade;
    @Column(nullable = false, length = 2)
    private String uf;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Pessoa pessoa;

    @Deprecated
    Endereco() {
    }

    public Endereco(String logradouro, String cep, Long numero, String cidade, String uf, Pessoa pessoa) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.uf = uf;
        this.pessoa = pessoa;
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

    public Long getId() {
        return id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }
}
