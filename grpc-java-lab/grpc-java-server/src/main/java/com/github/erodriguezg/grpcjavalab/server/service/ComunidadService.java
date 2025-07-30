package com.github.erodriguezg.grpcjavalab.server.service;

import com.github.erodriguezg.grpcjavalab.server.dto.ComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarComunidadDTO;
import org.springframework.data.domain.Page;

public interface ComunidadService {

    Page<ComunidadDTO> buscarComunidades(FiltroBuscarComunidadDTO filtro);

}
