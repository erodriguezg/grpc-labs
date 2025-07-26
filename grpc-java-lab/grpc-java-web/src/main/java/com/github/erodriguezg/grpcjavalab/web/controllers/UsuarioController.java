package com.github.erodriguezg.grpcjavalab.web.controllers;

import com.github.erodriguezg.grpcjavalab.api.proto.UsuarioRequest;
import com.github.erodriguezg.grpcjavalab.api.proto.UsuarioServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceGrpc;

    @GetMapping(value = "/test-grpc", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> testGrpc() {
        var requestGrpc = UsuarioRequest.newBuilder().setNombre("Eduardo").build();
        var responseGrpc = usuarioServiceGrpc.crearUsuario(requestGrpc);
        return ResponseEntity.ok("grpc dice: " + responseGrpc.getMensaje());
    }

}
