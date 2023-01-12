package com.pessoas_api.pessoas_api.core.services.endereco.forms;

import com.pessoas_api.pessoas_api.core.entities.Endereco;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import jakarta.validation.constraints.*;

public class EnderecoCreationForm {
    @NotNull
    @NotEmpty
    private String logradouro;
    @NotNull
    @Pattern(regexp = "[\\d]{8}") //8 dígitos numéricos
    private String cep;
    @Min(0)
    private Long numero;
    @NotNull
    @Size(min = 2)
    private String cidade;
    @NotNull
    @Size(min = 2, max = 2)
    private String uf;
    @NotNull
    private Boolean principal;

    public EnderecoCreationForm() {

    }

    EnderecoCreationForm(String logradouro, String cep, Long numero, String cidade, String uf, Boolean principal) {
        this.logradouro = logradouro;
        this.cep = cep;
        this.numero = numero;
        this.cidade = cidade;
        this.uf = uf;
        this.principal = principal;
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

    public Boolean getPrincipal() {
        return principal;
    }

    public Endereco convertForPessoa(Pessoa pessoa) {
        Endereco endereco = new Endereco(logradouro, cep, numero, cidade, uf, pessoa);
        if (this.principal) pessoa.addEnderecoPrincipal(endereco);
        else pessoa.addEndereco(endereco);
        return endereco;
    }
}
