package com.github.erodriguezg.grpcjavalab.server.repository;

import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarPersonaDTO;
import com.github.erodriguezg.grpcjavalab.server.dto.PersonaDTO;
import org.springframework.data.domain.Page;

public interface PersonaRepositoryCustom {

    Page<PersonaDTO> buscarPersonas(FiltroBuscarPersonaDTO filtro);

}
