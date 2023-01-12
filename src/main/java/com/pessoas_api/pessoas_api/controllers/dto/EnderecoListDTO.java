package com.pessoas_api.pessoas_api.controllers.dto;

import com.pessoas_api.pessoas_api.controllers.dto.EnderecoDTO;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EnderecoListDTO {
    private Long enderecoPrincipalId;
    private List<EnderecoDTO> enderecos;

    public EnderecoListDTO(Pessoa pessoa) {
        this.enderecoPrincipalId = pessoa.getEnderecoPrincipal().getId();
        enderecos = pessoa.getEnderecos()
                .stream()
                .map(EnderecoDTO::new)
                .collect(Collectors.toList());
    }

    public Long getEnderecoPrincipalId() {
        return enderecoPrincipalId;
    }

    public List<EnderecoDTO> getEnderecos() {
        return Collections.unmodifiableList(enderecos);
    }
}
