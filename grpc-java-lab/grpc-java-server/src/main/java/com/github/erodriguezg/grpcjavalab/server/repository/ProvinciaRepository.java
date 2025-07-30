package com.github.erodriguezg.grpcjavalab.server.repository;

import com.github.erodriguezg.grpcjavalab.server.entity.Provincia;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProvinciaRepository extends CrudRepository<Provincia, Integer> {

    List<Provincia> findByRegionIdOrderByNombreAsc(Integer regionId);

}
