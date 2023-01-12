package com.pessoas_api.pessoas_api.core.services.pessoa;

import com.pessoas_api.pessoas_api.controllers.PessoaEditService;
import com.pessoas_api.pessoas_api.controllers.exceptions.PessoaNotFoundException;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.controllers.forms.PessoaEditForm;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaEditServiceImpl implements PessoaEditService {
    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaEditServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void edit(PessoaEditForm form) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(form.getId());
        if (pessoa.isEmpty()) throw new PessoaNotFoundException("Pessoa com id %d não encontrado".formatted(form.getId()));

        pessoa.get().setNome(form.getNome());
        pessoa.get().setDataNascimento(form.getDataNascimento());

        pessoaRepository.save(pessoa.get());
    }
}
