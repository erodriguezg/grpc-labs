package com.github.erodriguezg.grpcjavalab.web.controllers;

import com.github.erodriguezg.grpcjavalab.api.grpc.BuscarComunidadesRequestMsg;
import com.github.erodriguezg.grpcjavalab.api.grpc.BuscarComunidadesResponseMsg;
import com.github.erodriguezg.grpcjavalab.api.grpc.ComunidadServiceGrpc;
import com.github.erodriguezg.grpcjavalab.web.vo.ComunidadVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/comunidad")
@RequiredArgsConstructor
public class ComunidadController {

    private final static Logger log = LoggerFactory.getLogger(ComunidadController.class);

    private final ComunidadServiceGrpc.ComunidadServiceBlockingStub comunidadServiceGrpc;

    @GetMapping("/gestionar")
    public String irGestionar(Model model) {
        var requestMsg = BuscarComunidadesRequestMsg.newBuilder()
                .setPageNumber(0)
                .setPageSize(10)
                .build();
        var responseMsg = comunidadServiceGrpc.buscarComunidades(requestMsg);
        var comunidades = toComunidadVOList(responseMsg);
        model.addAttribute("comunidades", comunidades);
        return "comunidad/gestionar.html";
    }

    // privates

    private List<ComunidadVO> toComunidadVOList(BuscarComunidadesResponseMsg responseMsg) {
        var comunidades = new ArrayList<ComunidadVO>();
        responseMsg.getItemsList().forEach(msg -> {
            var vo = new ComunidadVO();
            vo.setComunaId(msg.getComunaId());
            vo.setComunaNombre(msg.getComunaNombre());
            vo.setDireccion(msg.getDireccion());
            vo.setId(msg.getId());
            vo.setNombre(msg.getNombre());
            vo.setProvinciaId(msg.getProvinciaId());
            vo.setProvinciaNombre(msg.getProvinciaNombre());
            vo.setRegionId(msg.getRegionId());
            vo.setRegionNombre(msg.getRegionNombre());
            vo.setTipoId(msg.getTipoId());
            vo.setTipoNombre(msg.getTipoNombre());
            comunidades.add(vo);
        });
        return comunidades;
    }

}
