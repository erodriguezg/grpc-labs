package com.github.erodriguezg.grpcjavalab.server.grpc;

import com.github.erodriguezg.grpcjavalab.api.proto.UsuarioRequest;
import com.github.erodriguezg.grpcjavalab.api.proto.UsuarioResponse;
import com.github.erodriguezg.grpcjavalab.api.proto.UsuarioServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class UsuarioServiceGrpcImpl extends UsuarioServiceGrpc.UsuarioServiceImplBase {

    private static final Logger log = LoggerFactory.getLogger(UsuarioServiceGrpcImpl.class);


    @Override
    public void crearUsuario(UsuarioRequest request, StreamObserver<UsuarioResponse> responseObserver) {

        log.debug("Thread: {}", Thread.currentThread());

        UsuarioResponse response = UsuarioResponse.newBuilder()
                .setId(1L)
                .setMensaje("Usuario creado: " + request.getNombre())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
