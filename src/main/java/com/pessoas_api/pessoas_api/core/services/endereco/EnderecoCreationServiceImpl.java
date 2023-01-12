package com.pessoas_api.pessoas_api.core.services.endereco;

import com.pessoas_api.pessoas_api.controllers.EnderecoCreationService;
import com.pessoas_api.pessoas_api.controllers.exceptions.PessoaNotFoundException;
import com.pessoas_api.pessoas_api.core.entities.Endereco;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.controllers.forms.EnderecoCreationForm;
import com.pessoas_api.pessoas_api.repositories.EnderecoRepository;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EnderecoCreationServiceImpl implements EnderecoCreationService {
    private PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    EnderecoCreationServiceImpl(PessoaRepository pessoaRepository,
                                EnderecoRepository enderecoRepository) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco createFrom(EnderecoCreationForm form, Long pessoaId) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

        if (pessoa.isEmpty()) throw new PessoaNotFoundException("Pessoa com id %d não encontrado".formatted(pessoaId));
        Endereco endereco = form.convertForPessoa(pessoa.get());

        endereco = enderecoRepository.save(endereco);
        pessoaRepository.save(pessoa.get());

        return endereco;
    }
}
