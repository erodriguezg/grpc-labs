package com.github.erodriguezg.grpcjavalab.web.controllers;

import com.github.erodriguezg.grpcjavalab.api.proto.UsuarioRequest;
import com.github.erodriguezg.grpcjavalab.api.proto.UsuarioServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioServiceGrpc.UsuarioServiceBlockingStub usuarioServiceGrpc;

    @GetMapping(value = "/test-grpc")
    public String testGrpc(ModelMap model) {
        var requestGrpc = UsuarioRequest.newBuilder().setNombre("Eduardo").build();
        var responseGrpc = usuarioServiceGrpc.crearUsuario(requestGrpc);
        model.put("mensaje", responseGrpc.getMensaje());
        return "usuario";
    }

}
