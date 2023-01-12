package com.pessoas_api.pessoas_api.core.services.pessoa;

import com.pessoas_api.pessoas_api.controllers.exceptions.PessoaNotFoundException;
import com.pessoas_api.pessoas_api.controllers.forms.PessoaEditForm;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class PessoaEditServiceImplTest {

    @Mock
    private Pessoa pessoa;
    @Mock
    private PessoaEditForm form;
    @MockBean
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaEditServiceImpl pessoaEditService;

    @Test
    void shouldEditPessoaWithDataFromForm() {
        when(pessoaRepository.findById(any())).thenReturn(Optional.of(pessoa));
        when(form.getNome()).thenReturn("dummy");
        when(form.getDataNascimento()).thenReturn(LocalDate.of(2001, 01, 02));

        pessoaEditService.edit(form);

        verify(pessoa, times(1)).setNome(form.getNome());
        verify(pessoa, times(1)).setDataNascimento(form.getDataNascimento());
        verify(pessoaRepository, times(1)).save(pessoa);
    }

    @Test
    void shouldThrowPessoaNotFoundException() {
        when(pessoaRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(PessoaNotFoundException.class, () -> pessoaEditService.edit(form));
    }
}