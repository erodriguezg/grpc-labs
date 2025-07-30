package com.github.erodriguezg.grpcjavalab.server.service.impl;

import com.github.erodriguezg.grpcjavalab.server.entity.Comuna;
import com.github.erodriguezg.grpcjavalab.server.entity.Provincia;
import com.github.erodriguezg.grpcjavalab.server.entity.Region;
import com.github.erodriguezg.grpcjavalab.server.repository.ComunaRepository;
import com.github.erodriguezg.grpcjavalab.server.repository.ProvinciaRepository;
import com.github.erodriguezg.grpcjavalab.server.repository.RegionRepository;
import com.github.erodriguezg.grpcjavalab.server.service.TerritorioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TerritorioServiceImpl implements TerritorioService {

    private final RegionRepository regionRepository;

    private final ProvinciaRepository provinciaRepository;

    private final ComunaRepository comunaRepository;

    @Override
    public List<Region> traerRegiones() {
        return regionRepository.findByOrderByNombreAsc();
    }

    @Override
    public List<Provincia> traerProvinciasPorRegion(Region region) {
        if (region != null) {
            return provinciaRepository.findByRegionIdOrderByNombreAsc(region.getIdRegion());
        } else {
            return List.of();
        }
    }

    @Override
    public List<Comuna> traerComunasPorProvincia(Provincia provincia) {
        if(provincia != null) {
            return comunaRepository.findByProvinciaIdOrderByNombreAsc(provincia.getIdProvincia());
        } else {
            return List.of();
        }
    }
}
