package com.pessoas_api.pessoas_api.controllers;

import com.pessoas_api.pessoas_api.core.entities.Endereco;
import com.pessoas_api.pessoas_api.controllers.forms.EnderecoCreationForm;

public interface EnderecoCreationService {
    Endereco createFrom(EnderecoCreationForm form, Long pessoaId);
}
