package com.github.erodriguezg.grpcjavalab.server.repository;

import com.github.erodriguezg.grpcjavalab.server.entity.ComunidadTipo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComunidadTipoRepository extends CrudRepository<ComunidadTipo, Integer> {

    List<ComunidadTipo> findByOrderByNombreAsc();

}
