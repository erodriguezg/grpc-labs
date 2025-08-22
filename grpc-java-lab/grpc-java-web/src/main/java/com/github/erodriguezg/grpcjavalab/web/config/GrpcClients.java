package com.github.erodriguezg.grpcjavalab.web.config;

import com.github.erodriguezg.grpcjavalab.api.grpc.ComunidadServiceGrpc;
import com.github.erodriguezg.grpcjavalab.api.grpc.TerritorioServiceGrpc;
import com.github.erodriguezg.grpcjavalab.api.proto.UsuarioServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClients {

    @GrpcClient("usuario-service")
    private UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceStub;

    @GrpcClient("comunidad-service")
    private ComunidadServiceGrpc.ComunidadServiceBlockingStub comunidadServiceStub;

    @GrpcClient("territorio-service")
    private TerritorioServiceGrpc.TerritorioServiceBlockingStub territorioServiceStub;

    @Bean
    public UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceGrpc() {
        return usuarioServiceStub;
    }

    @Bean
    public ComunidadServiceGrpc.ComunidadServiceBlockingStub comunidadServiceGrpc() {
        return comunidadServiceStub;
    }

    @Bean
    public TerritorioServiceGrpc.TerritorioServiceBlockingStub territorioServiceGrpc() { return territorioServiceStub; }

}
