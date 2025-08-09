package com.github.erodriguezg.grpcjavalab.web.controllers;

import com.github.erodriguezg.grpcjavalab.api.grpc.BuscarComunidadesRequestMsg;
import com.github.erodriguezg.grpcjavalab.api.grpc.BuscarComunidadesResponseMsg;
import com.github.erodriguezg.grpcjavalab.api.grpc.ComunidadServiceGrpc;
import com.github.erodriguezg.grpcjavalab.web.vo.ComunidadVO;
import com.github.erodriguezg.grpcjavalab.web.vo.FiltroComunidadVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static com.github.erodriguezg.grpcjavalab.web.utils.StringUtil.trimOrBlank;

@Controller
@RequestMapping("/comunidad/")
@RequiredArgsConstructor
public class GestionarComunidadController {

    private final static Logger log = LoggerFactory.getLogger(GestionarComunidadController.class);

    private static final int PAGE_SIZE_COMUNIDADES = 10;

    private final ComunidadServiceGrpc.ComunidadServiceBlockingStub comunidadServiceGrpc;

    @GetMapping("/gestionar")
    public String irGestionar(Model model) {

        var filtro = FiltroComunidadVO.builder()
                .pageNumber(0)
                .pageSize(PAGE_SIZE_COMUNIDADES)
                .build();
        var comunidades = buscarComunidades(filtro);
        model.addAttribute("filtro", filtro);
        model.addAttribute("comunidades", comunidades);
        return "comunidad/gestionar.html";
    }

    public String buscar(FiltroComunidadVO filtro, Model model) {
        var comunidades = buscarComunidades(filtro);
        model.addAttribute("filtro", filtro);
        model.addAttribute("comunidades", comunidades);
        return "comunidad/gestionar.html :: filterAndTable";
    }

    // privates

    private List<ComunidadVO> buscarComunidades(FiltroComunidadVO filtro) {
        var requestMsg = BuscarComunidadesRequestMsg.newBuilder()
                .setPageNumber(filtro.getPageNumber())
                .setPageSize(filtro.getPageSize())
                .setDireccion(trimOrBlank(filtro.getDireccion()))
                .setIdComunidad(trimOrBlank(filtro.getId()))
                .setIdComuna(filtro.getComunaId())
                .setIdComunidadTipo(filtro.getTipoId())
                .setIdProvincia(filtro.getProvinciaId())
                .setIdRegion(filtro.getRegionId())
                .setNombre(trimOrBlank(filtro.getNombre()))
                .build();
        var responseMsg = comunidadServiceGrpc.buscarComunidades(requestMsg);
        return toComunidadVOList(responseMsg);
    }

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
