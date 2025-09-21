package com.github.erodriguezg.grpcjavalab.server.service.impl;

import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarPersonaDTO;
import com.github.erodriguezg.grpcjavalab.server.dto.PersonaDTO;
import com.github.erodriguezg.grpcjavalab.server.service.PersonaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PersonaServiceImpl implements PersonaService {

    @Transactional(readOnly = true)
    @Override
    public PersonaDTO traerPersonaPorId(UUID idPersona) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<PersonaDTO> buscarPersonas(FiltroBuscarPersonaDTO filtro) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public void guardarPersona(UUID idComunidad, PersonaDTO persona) {

    }
}
