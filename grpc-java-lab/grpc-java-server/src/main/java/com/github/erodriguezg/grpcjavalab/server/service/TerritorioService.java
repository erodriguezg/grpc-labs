package com.github.erodriguezg.grpcjavalab.server.service;

import com.github.erodriguezg.grpcjavalab.server.entity.Comuna;
import com.github.erodriguezg.grpcjavalab.server.entity.Provincia;
import com.github.erodriguezg.grpcjavalab.server.entity.Region;

import java.util.List;

public interface TerritorioService {

    List<Region> traerRegiones();

    List<Provincia> traerProvinciasPorRegion(Region region);

    List<Comuna> traerComunasPorProvincia(Provincia provincia);

}
