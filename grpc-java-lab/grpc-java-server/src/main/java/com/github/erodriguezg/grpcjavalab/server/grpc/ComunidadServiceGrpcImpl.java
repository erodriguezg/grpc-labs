package com.github.erodriguezg.grpcjavalab.server.grpc;

import com.github.erodriguezg.grpcjavalab.api.proto.ComunidadPage;
import com.github.erodriguezg.grpcjavalab.api.proto.ComunidadServiceGrpc;
import com.github.erodriguezg.grpcjavalab.api.proto.FiltroBuscarComunidad;
import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.entity.Comunidad;
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
    public void buscarComunidades(FiltroBuscarComunidad request, StreamObserver<ComunidadPage> responseObserver) {
        log.debug("-> buscarComunidades. request: {}", request );
        var filtroDTO = toFiltroDTO(request);
        var comunidadesPage = comunidadService.buscarComunidades(filtroDTO);
        var comunidadPageResponse = toComunidadPageResponse(comunidadesPage);
        responseObserver.onNext(comunidadPageResponse);
        responseObserver.onCompleted();
    }

    // privates

    private FiltroBuscarComunidadDTO toFiltroDTO(FiltroBuscarComunidad request) {
        var filtroDTO = new FiltroBuscarComunidadDTO();
        filtroDTO.setIdComunidad(UUID.fromString(request.getIdComunidad()));
        filtroDTO.setDireccion(request.getDireccion());
        filtroDTO.setNombre(request.getNombre());
        filtroDTO.setIdComunidadTipo(request.getIdComunidadTipo());
        filtroDTO.setIdComuna(request.getIdComuna());
        filtroDTO.setPageNumber(request.getPageNumber());
        filtroDTO.setPageSize(request.getPageSize());
        return filtroDTO;
    }

    private ComunidadPage toComunidadPageResponse(Page<Comunidad> comunidadPage) {
        var builder = ComunidadPage.newBuilder();
        for(int i = 0; i < comunidadPage.getContent().size(); i++) {
            var comunidad = comunidadPage.getContent().get(i);
            var comunidadBuilder = com.github.erodriguezg.grpcjavalab.api.proto.Comunidad.newBuilder();
            var comunidadProto = comunidadBuilder
                    .setIdComunidad(comunidad.getIdComunidad().toString())
                    .setComunidadTipoId(comunidad.getComunidadTipoId())
                    .setComunaId(comunidad.getComunaId())
                    .setNombre(comunidad.getNombre())
                    .setDireccion(comunidad.getDireccion());
            builder.setItems(i, comunidadProto);
        }

        builder.setPageNumber(comunidadPage.getPageable().getPageNumber());
        builder.setPageSize(comunidadPage.getPageable().getPageSize());
        builder.setTotalPages(comunidadPage.getTotalPages());
        builder.setTotalElements(comunidadPage.getTotalElements());
        return builder.build();
    }
}
