package com.pessoas_api.pessoas_api.core.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PessoaCreationFormTest {
    private static final String NOME = "nome sobrenome";
    private static final LocalDate NASCIMENTO = LocalDate.of(2000, 1, 1);
    private static final String LOGRADOURO = "rua legal";
    private static final String CEP = "12345678";
    private static final Long NUMERO = 99l;
    private static final String CIDADE = "cidade";
    private static final String UF = "uf";
    
    private PessoaCreationForm form;

    @BeforeEach
    void setup() {
        form = new PessoaCreationForm(
                NASCIMENTO,
                NOME,
                LOGRADOURO,
                CEP,
                NUMERO,
                CIDADE,
                UF
        );
    }

    @Test
    void shouldCreatePessoaWithDataFromForm() {
        Pessoa pessoa = form.convert();

        assertEquals(NOME, pessoa.getNome());
        assertEquals(NASCIMENTO, pessoa.getDataNascimento());
        assertEquals(LOGRADOURO, pessoa.getEnderecoPrincipal().getLogradouro());
        assertEquals(CEP, pessoa.getEnderecoPrincipal().getCep());
        assertEquals(NUMERO, pessoa.getEnderecoPrincipal().getNumero());
        assertEquals(CIDADE, pessoa.getEnderecoPrincipal().getCidade());
        assertEquals(UF, pessoa.getEnderecoPrincipal().getUf());
    }
}