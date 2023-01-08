package com.pessoas_api.pessoas_api.core.entities;

import jakarta.persistence.*;

import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Entity
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private LocalDate dataNascimento;
    @OneToMany(cascade = CascadeType.ALL)
    @Size(min = 1)
    private List<Endereco> enderecos = new ArrayList<>();
    @OneToOne(optional = false)
    private Endereco enderecoPrincipal;

    @Deprecated
    Pessoa() {
    }

    public Pessoa(String nome, LocalDate dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
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

    public List<Endereco> getEnderecos() {
        return Collections.unmodifiableList(this.enderecos);
    }

    public Endereco addEndereco(Endereco endereco) {
        if (this.equals(endereco.getPessoa())) {
            this.enderecos.add(endereco);
            if (this.enderecos.size() == 1) enderecoPrincipal = endereco;
            return endereco;
        }
        throw new IllegalArgumentException("O endereço só pode ser adicionado apenas à pessoa que o possui.");
    }

    public Endereco getEnderecoPrincipal() {
        return enderecoPrincipal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id, pessoa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
