package com.github.erodriguezg.grpcjavalab.server.repository;

import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.entity.Comunidad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ComunidadRepositoryCustom {

    Page<Comunidad> buscarComunidades(FiltroBuscarComunidadDTO filtro);

}
