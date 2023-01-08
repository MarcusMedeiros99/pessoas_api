package com.pessoas_api.pessoas_api.controllers;

import com.pessoas_api.pessoas_api.controllers.dto.EnderecoDTO;
import com.pessoas_api.pessoas_api.controllers.dto.PessoaDTO;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.core.services.PessoaCreationForm;
import com.pessoas_api.pessoas_api.core.services.PessoaCreationService;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    private PessoaCreationService pessoaCreationService;
    private PessoaRepository pessoaRepository;

    @Autowired
    public PessoaController(PessoaCreationService pessoaCreationService, PessoaRepository pessoaRepository) {
        this.pessoaCreationService = pessoaCreationService;
        this.pessoaRepository = pessoaRepository;
    }

    @PostMapping
    ResponseEntity<PessoaDTO> criar(@RequestBody @Valid PessoaCreationForm form,
                                    UriComponentsBuilder uriBuilder) {
        Pessoa novaPessoa = pessoaCreationService.createFrom(form);

        return ResponseEntity
                .created(uriBuilder
                        .path("pessoas/{id}")
                        .buildAndExpand(novaPessoa.getId())
                        .toUri())
                .body(new PessoaDTO(novaPessoa));
    }

    @GetMapping("/{id}")
    ResponseEntity<PessoaDTO> encontrarPorId(@PathVariable Long id) {
        Optional<Pessoa> pessoa = pessoaRepository.findById(id);

        if (pessoa.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new PessoaDTO(pessoa.get()));
    }

    @GetMapping
    ResponseEntity<List<PessoaDTO>> listar() {
        List<PessoaDTO> pessoas = pessoaRepository
                .findAll()
                .stream()
                .map(PessoaDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(pessoas);
    }
}
