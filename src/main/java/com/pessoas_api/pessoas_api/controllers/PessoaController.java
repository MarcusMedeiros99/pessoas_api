package com.pessoas_api.pessoas_api.controllers;

import com.pessoas_api.pessoas_api.controllers.dto.ErrorDTO;
import com.pessoas_api.pessoas_api.controllers.dto.PessoaDTO;
import com.pessoas_api.pessoas_api.controllers.exceptions.PessoaNotFoundException;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.core.services.pessoa.forms.PessoaCreationForm;
import com.pessoas_api.pessoas_api.core.services.pessoa.PessoaCreationService;
import com.pessoas_api.pessoas_api.core.services.pessoa.forms.PessoaEditForm;
import com.pessoas_api.pessoas_api.core.services.pessoa.PessoaEditService;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private PessoaEditService pessoaEditService;

    @Autowired
    public PessoaController(PessoaCreationService pessoaCreationService, PessoaRepository pessoaRepository, PessoaEditService pessoaEditService) {
        this.pessoaCreationService = pessoaCreationService;
        this.pessoaRepository = pessoaRepository;
        this.pessoaEditService = pessoaEditService;
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

    @PutMapping
    ResponseEntity<Void> edit(@RequestBody @Valid PessoaEditForm form) {
        pessoaEditService.edit(form);

        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(PessoaNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ErrorDTO handlePessoaNotFoundException(PessoaNotFoundException exception) {
        return new ErrorDTO(exception.getMessage());
    }
}
