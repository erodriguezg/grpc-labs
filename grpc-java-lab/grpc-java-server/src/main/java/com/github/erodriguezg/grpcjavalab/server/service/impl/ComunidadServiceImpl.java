package com.github.erodriguezg.grpcjavalab.server.service.impl;

import com.github.erodriguezg.grpcjavalab.server.dto.ComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.repository.ComunidadRepository;
import com.github.erodriguezg.grpcjavalab.server.service.ComunidadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComunidadServiceImpl implements ComunidadService {

    private final ComunidadRepository comunidadRepository;

    @Transactional(readOnly = true)
    @Override
    public Page<ComunidadDTO> buscarComunidades(FiltroBuscarComunidadDTO filtro) {
        return comunidadRepository.buscarComunidades(filtro);
    }
}
