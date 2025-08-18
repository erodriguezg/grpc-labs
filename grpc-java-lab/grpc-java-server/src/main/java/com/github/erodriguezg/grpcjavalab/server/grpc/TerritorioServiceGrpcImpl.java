package com.github.erodriguezg.grpcjavalab.server.grpc;

import com.github.erodriguezg.grpcjavalab.api.grpc.*;
import com.github.erodriguezg.grpcjavalab.server.entity.Comuna;
import com.github.erodriguezg.grpcjavalab.server.entity.Provincia;
import com.github.erodriguezg.grpcjavalab.server.entity.Region;
import com.github.erodriguezg.grpcjavalab.server.service.TerritorioService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@GrpcService
@RequiredArgsConstructor
public class TerritorioServiceGrpcImpl extends TerritorioServiceGrpc.TerritorioServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(TerritorioServiceGrpcImpl.class);

    private final TerritorioService territorioService;

    @Override
    public void traerRegiones(TraerRegionesRequestMsg request, StreamObserver<TraerRegionesResponseMsg> responseObserver) {
        log.debug("-> traerRegiones");
        var regiones = territorioService.traerRegiones();
        var responseMsg = toRegionesResponseMsg(regiones);

        responseObserver.onNext(responseMsg);
        responseObserver.onCompleted();
    }

    @Override
    public void traerProvinciasPorRegion(TraerProvinciasRequestMsg request, StreamObserver<TraerProvinciasResponseMsg> responseObserver) {
        log.debug("-> traer provincias. idRegion : {}", request.getRegionId());
        var regionIn = Region.builder().idRegion(request.getRegionId()).build();
        var provincias = territorioService.traerProvinciasPorRegion(regionIn);
        var responseMsg = toProvinciasResponseMsg(provincias);

        responseObserver.onNext(responseMsg);
        responseObserver.onCompleted();
    }

    @Override
    public void traerComunasPorProvincia(TraerComunasRequestMsg request, StreamObserver<TraerComunasResponseMsg> responseObserver) {
        log.debug("-> traer comunas. idProvincia: {}", request.getProvinciaId());
        var provinciaIn = Provincia.builder().idProvincia(request.getProvinciaId()).build();
        var comunas = territorioService.traerComunasPorProvincia(provinciaIn);
        var responseMsg = toComunasResponseMsg(comunas);

        responseObserver.onNext(responseMsg);
        responseObserver.onCompleted();
    }

    // privates

    private TraerRegionesResponseMsg toRegionesResponseMsg(List<Region> regiones) {
        var builder = TraerRegionesResponseMsg.newBuilder();
        regiones.forEach(r -> {
            var regionMsg = RegionMsg.newBuilder()
                    .setIdRegion(r.getIdRegion())
                    .setNombre(r.getNombre())
                    .build();
            builder.addRegiones(regionMsg);
        });
        return builder.build();
    }

    private TraerProvinciasResponseMsg toProvinciasResponseMsg(List<Provincia> provincias) {
        var builder = TraerProvinciasResponseMsg.newBuilder();
        provincias.forEach(p -> {
            var provinciaMsg = ProvinciaMsg.newBuilder()
                    .setIdProvincia(p.getIdProvincia())
                    .setRegionId(p.getRegionId())
                    .setNombre(p.getNombre())
                    .build();
            builder.addProvincias(provinciaMsg);
        });
        return builder.build();
    }

    private TraerComunasResponseMsg toComunasResponseMsg(List<Comuna> comunas) {
        var builder = TraerComunasResponseMsg.newBuilder();
        comunas.forEach(c -> {
            var comunaMsg = ComunaMsg.newBuilder()
                    .setIdComuna(c.getIdComuna())
                    .setProvinciaId(c.getProvinciaId())
                    .setNombre(c.getNombre())
                    .build();
            builder.addComunas(comunaMsg);
        });
        return builder.build();
    }
    
}
