package com.github.erodriguezg.grpcjavalab.server.repository.impl;

import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarPersonaDTO;
import com.github.erodriguezg.grpcjavalab.server.dto.PersonaDTO;
import com.github.erodriguezg.grpcjavalab.server.repository.PersonaRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class PersonaRepositoryImpl implements PersonaRepositoryCustom {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public PersonaRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Page<PersonaDTO> buscarPersonas(FiltroBuscarPersonaDTO filtro) {
        return null;
    }

}
