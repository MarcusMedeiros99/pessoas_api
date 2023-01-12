package com.pessoas_api.pessoas_api.controllers;

import com.pessoas_api.pessoas_api.controllers.dto.EnderecoDTO;
import com.pessoas_api.pessoas_api.controllers.dto.EnderecoListDTO;
import com.pessoas_api.pessoas_api.core.entities.Endereco;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.core.services.EnderecoCreationForm;
import com.pessoas_api.pessoas_api.core.services.EnderecoCreationService;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/pessoas/{pessoaId}/enderecos")
public class EnderecoController {

    private PessoaRepository pessoaRepository;
    private EnderecoCreationService enderecoCreationService;

    @Autowired
    public EnderecoController(PessoaRepository pessoaRepository, EnderecoCreationService enderecoCreationService) {
        this.pessoaRepository = pessoaRepository;
        this.enderecoCreationService = enderecoCreationService;
    }

    @GetMapping
    ResponseEntity<EnderecoListDTO> listarPorPessoa(@PathVariable Long pessoaId) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

        if (pessoa.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new EnderecoListDTO(pessoa.get()));
    }

    @PostMapping
    ResponseEntity<EnderecoDTO> criar(@PathVariable Long pessoaId, @RequestBody @Valid EnderecoCreationForm form,
                                      UriComponentsBuilder uriBuilder) {
        Endereco endereco = enderecoCreationService.createFrom(form, pessoaId);

        return ResponseEntity
                .created(uriBuilder
                        .path("pessoas/{pessoaId}/enderecos/{enderecoId}")
                        .buildAndExpand(pessoaId, endereco.getId())
                        .toUri())
                .body(new EnderecoDTO(endereco));
    }

    @GetMapping("/principal")
    ResponseEntity<EnderecoDTO> encontrarEnderecoPrincipal(@PathVariable Long pessoaId) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId    );
        if (pessoa.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity
                .ok(new EnderecoDTO(pessoa.get().getEnderecoPrincipal()));
    }

}
