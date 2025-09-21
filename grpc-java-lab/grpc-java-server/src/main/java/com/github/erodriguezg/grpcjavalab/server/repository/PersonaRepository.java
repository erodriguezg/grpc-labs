package com.github.erodriguezg.grpcjavalab.server.repository;

import com.github.erodriguezg.grpcjavalab.server.entity.Persona;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonaRepository extends CrudRepository<Persona, UUID>, PersonaRepositoryCustom {
}
