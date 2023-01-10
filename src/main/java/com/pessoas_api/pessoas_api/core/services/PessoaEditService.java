package com.pessoas_api.pessoas_api.core.services;

import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaEditService {
    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaEditService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public void edit(PessoaEditForm form) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(form.getId());
        if (pessoa.isEmpty()) throw new IllegalArgumentException("Id not found");

        pessoa.get().setNome(form.getNome());
        pessoa.get().setDataNascimento(form.getDataNascimento());

        pessoaRepository.save(pessoa.get());
    }
}
