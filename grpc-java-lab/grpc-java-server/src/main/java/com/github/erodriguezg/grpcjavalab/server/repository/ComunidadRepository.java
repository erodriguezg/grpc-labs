package com.github.erodriguezg.grpcjavalab.server.repository;

import com.github.erodriguezg.grpcjavalab.server.entity.Comunidad;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ComunidadRepository extends CrudRepository<Comunidad, UUID>, ComunidadRepositoryCustom {

}
