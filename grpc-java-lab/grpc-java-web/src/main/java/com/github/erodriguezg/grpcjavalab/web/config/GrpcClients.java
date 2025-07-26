package com.github.erodriguezg.grpcjavalab.web.config;

import com.github.erodriguezg.grpcjavalab.api.proto.UsuarioServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcClients {

    @GrpcClient("usuario-service")
    private UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceStub;

    @Bean
    public UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceGrpc() {
        return usuarioServiceStub;
    }

}
