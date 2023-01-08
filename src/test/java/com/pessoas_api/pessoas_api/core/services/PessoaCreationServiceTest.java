package com.pessoas_api.pessoas_api.core.services;

import com.pessoas_api.pessoas_api.core.entities.Endereco;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class PessoaCreationServiceTest {

    @Autowired
    private PessoaCreationService pessoaCreationService;
    @Mock
    private PessoaCreationForm pessoaCreationForm;
    @MockBean
    private PessoaRepository pessoaRepository;
    @Mock
    private Pessoa pessoa;

    @Test
    void shouldCreatePessoaFromForm() {
        when(pessoaCreationForm.convert()).thenReturn(pessoa);
        when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
        when(pessoa.getId()).thenReturn(99l);
        Pessoa novaPessoa = pessoaCreationService.createFrom(pessoaCreationForm);

        assertEquals(pessoa, novaPessoa);
    }

    @Test
    void shouldSavePessoaWhenCreateFromForm() {
        when(pessoaCreationForm.convert()).thenReturn(pessoa);
        pessoaCreationService.createFrom(pessoaCreationForm);

        verify(pessoaRepository, times(1)).save(pessoa);
    }
}