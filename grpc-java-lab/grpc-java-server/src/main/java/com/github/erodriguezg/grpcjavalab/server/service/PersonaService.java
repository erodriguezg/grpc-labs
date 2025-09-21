package com.github.erodriguezg.grpcjavalab.server.service;

import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarPersonaDTO;
import com.github.erodriguezg.grpcjavalab.server.dto.PersonaDTO;
import org.springframework.data.domain.Page;

import java.util.UUID;


public interface PersonaService {

    PersonaDTO traerPersonaPorId(UUID idPersona);

    Page<PersonaDTO> buscarPersonas(FiltroBuscarPersonaDTO filtro);

    void guardarPersona(UUID idComunidad, PersonaDTO persona);

}
