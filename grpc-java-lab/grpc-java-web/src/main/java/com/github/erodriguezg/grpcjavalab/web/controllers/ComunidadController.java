package com.github.erodriguezg.grpcjavalab.web.controllers;

import com.github.erodriguezg.grpcjavalab.api.grpc.ComunidadServiceGrpc;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comunidad")
@RequiredArgsConstructor
public class ComunidadController {

    private final static Logger log = LoggerFactory.getLogger(ComunidadController.class);

    private final ComunidadServiceGrpc.ComunidadServiceBlockingStub comunidadServiceGrpc;

    @GetMapping("/gestionar")
    public String irGestionar() {
        return "comunidad/gestionar.html";
    }

}
