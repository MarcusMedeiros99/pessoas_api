package com.pessoas_api.pessoas_api.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pessoas_api.pessoas_api.controllers.dto.PessoaDTO;
import com.pessoas_api.pessoas_api.core.entities.Endereco;
import com.pessoas_api.pessoas_api.core.entities.Pessoa;
import com.pessoas_api.pessoas_api.repositories.PessoaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerTest {
    private static final Long UNEXISTING_ID = 99l;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PessoaController pessoaController;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private ObjectMapper mapper;

    @Test
    void shouldFindById() throws Exception {
        Pessoa pessoa = new Pessoa("nome", LocalDate.of(2001, 01, 03));
        pessoa.addEndereco(new Endereco("aa" ,"12345678", 1l, "sanca", "SP", pessoa));
        pessoaRepository.save(pessoa);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/pessoas/" + pessoa.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        PessoaDTO pessoaResponse = mapper.readValue(response, new TypeReference<PessoaDTO>() {});

        assertEquals(pessoa.getId(), pessoaResponse.getId());
    }

    @Test
    void shouldStatusNotFoundWhenFindUnexistingPessoa() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/pessoas/" + UNEXISTING_ID))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andReturn();
    }

}