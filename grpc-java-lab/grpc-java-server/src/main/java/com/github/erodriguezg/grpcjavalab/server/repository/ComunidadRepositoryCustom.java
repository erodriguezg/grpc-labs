package com.github.erodriguezg.grpcjavalab.server.repository;

import com.github.erodriguezg.grpcjavalab.server.dto.ComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarComunidadDTO;
import org.springframework.data.domain.Page;

public interface ComunidadRepositoryCustom {

    Page<ComunidadDTO> buscarComunidades(FiltroBuscarComunidadDTO filtro);

}
