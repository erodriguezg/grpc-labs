package com.github.erodriguezg.grpcjavalab.server.repository;

import com.github.erodriguezg.grpcjavalab.server.entity.Region;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RegionRepository extends CrudRepository<Region, Integer> {

    List<Region> findByOrderByNombreAsc();

}
