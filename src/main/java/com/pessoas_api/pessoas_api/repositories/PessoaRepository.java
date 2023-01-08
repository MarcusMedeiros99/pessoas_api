package com.pessoas_api.pessoas_api.repositories;

import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
