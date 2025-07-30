package com.github.erodriguezg.grpcjavalab.server.service;

import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.entity.Comunidad;
import org.springframework.data.domain.Page;

public interface ComunidadService {

    Page<Comunidad> buscarComunidades(FiltroBuscarComunidadDTO filtro);

}
