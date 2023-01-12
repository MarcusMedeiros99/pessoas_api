package com.pessoas_api.pessoas_api.controllers;

import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.controllers.forms.PessoaCreationForm;

public interface PessoaCreationService {
    Pessoa createFrom(PessoaCreationForm form);
}
