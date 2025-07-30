package com.github.erodriguezg.grpcjavalab.server.repository;

import com.github.erodriguezg.grpcjavalab.server.entity.Comuna;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ComunaRepository extends CrudRepository<Comuna, Integer> {

    List<Comuna> findByProvinciaIdOrderByNombreAsc(Integer provinciaId);

}
