package com.pessoas_api.pessoas_api.core.services.pessoa;

import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.core.services.pessoa.forms.PessoaCreationForm;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaCreationService {
    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaCreationService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public Pessoa createFrom(PessoaCreationForm form) {
        return pessoaRepository.save(form.convert());
    }
}