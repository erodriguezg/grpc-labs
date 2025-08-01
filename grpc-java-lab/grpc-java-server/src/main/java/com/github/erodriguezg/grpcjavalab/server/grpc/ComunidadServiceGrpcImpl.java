package com.github.erodriguezg.grpcjavalab.server.grpc;

import com.github.erodriguezg.grpcjavalab.api.grpc.BuscarComunidadesRequestMsg;
import com.github.erodriguezg.grpcjavalab.api.grpc.BuscarComunidadesResponseMsg;
import com.github.erodriguezg.grpcjavalab.api.grpc.ComunidadMsg;
import com.github.erodriguezg.grpcjavalab.api.grpc.ComunidadServiceGrpc;
import com.github.erodriguezg.grpcjavalab.server.dto.ComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.service.ComunidadService;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;

import java.util.UUID;

@GrpcService
@RequiredArgsConstructor
public class ComunidadServiceGrpcImpl extends ComunidadServiceGrpc.ComunidadServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(ComunidadServiceGrpcImpl.class);

    private final ComunidadService comunidadService;

    @Override
    public void buscarComunidades(BuscarComunidadesRequestMsg request, StreamObserver<BuscarComunidadesResponseMsg> responseObserver) {
        log.debug("-> buscarComunidades. request: {}", request);
        var filtroDTO = toFiltroDTO(request);
        var comunidadesPage = comunidadService.buscarComunidades(filtroDTO);
        var comunidadPageResponse = toComunidadPageResponse(comunidadesPage);
        responseObserver.onNext(comunidadPageResponse);
        responseObserver.onCompleted();
    }

    // privates

    private FiltroBuscarComunidadDTO toFiltroDTO(BuscarComunidadesRequestMsg request) {
        var filtroDTO = new FiltroBuscarComunidadDTO();
        if (!request.getIdComunidad().isBlank()) {
            filtroDTO.setIdComunidad(UUID.fromString(request.getIdComunidad()));
        }
        if (!request.getDireccion().isBlank()) filtroDTO.setDireccion(request.getDireccion());
        if (!request.getNombre().isBlank()) filtroDTO.setNombre(request.getNombre());
        if (request.getIdComunidadTipo() != 0) filtroDTO.setIdComunidadTipo(request.getIdComunidadTipo());
        if (request.getIdComuna() != 0) filtroDTO.setIdComuna(request.getIdComuna());
        if (request.getIdProvincia() != 0) filtroDTO.setIdProvincia(request.getIdProvincia());
        if (request.getIdRegion() != 0) filtroDTO.setIdRegion(request.getIdRegion());
        filtroDTO.setPageNumber(request.getPageNumber());
        filtroDTO.setPageSize(request.getPageSize());
        return filtroDTO;
    }

    private BuscarComunidadesResponseMsg toComunidadPageResponse(Page<ComunidadDTO> comunidadPage) {
        var builder = BuscarComunidadesResponseMsg.newBuilder();
        builder.setPageNumber(comunidadPage.getPageable().getPageNumber());
        builder.setPageSize(comunidadPage.getPageable().getPageSize());
        builder.setTotalPages(comunidadPage.getTotalPages());
        builder.setTotalElements(comunidadPage.getTotalElements());

        comunidadPage.get().forEach(dto -> {
            var comunidadMsg = ComunidadMsg.newBuilder()
                    .setComunaId(dto.getComunaId())
                    .setComunaNombre(dto.getComunaNombre())
                    .setDireccion(dto.getDireccion())
                    .setId(dto.getId().toString())
                    .setNombre(dto.getNombre())
                    .setProvinciaId(dto.getProvinciaId())
                    .setProvinciaNombre(dto.getProvinciaNombre())
                    .setRegionId(dto.getRegionId())
                    .setRegionNombre(dto.getRegionNombre())
                    .setTipoId(dto.getTipoId())
                    .setTipoNombre(dto.getTipoNombre())
                    .build();
            builder.addItems(comunidadMsg);
        });

        return builder.build();
    }
}
