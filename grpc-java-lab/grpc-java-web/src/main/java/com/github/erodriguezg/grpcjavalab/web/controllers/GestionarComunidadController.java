package com.github.erodriguezg.grpcjavalab.web.controllers;

import com.github.erodriguezg.grpcjavalab.api.grpc.BuscarComunidadesRequestMsg;
import com.github.erodriguezg.grpcjavalab.api.grpc.BuscarComunidadesResponseMsg;
import com.github.erodriguezg.grpcjavalab.api.grpc.ComunidadServiceGrpc;
import com.github.erodriguezg.grpcjavalab.web.vo.ComunidadVO;
import com.github.erodriguezg.grpcjavalab.web.vo.FiltroComunidadVO;
import com.github.erodriguezg.grpcjavalab.web.vo.PaginatedVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        var paginated = PaginatedVO.builder()
                .pageNumber(0)
                .pageSize(PAGE_SIZE_COMUNIDADES)
                .build();

        var filtro = FiltroComunidadVO.builder()
                .paginated(paginated)
                .build();

        buscar(filtro, model);
        return "comunidad/gestionar.html"; // vista completa en la primera carga
    }

    @PostMapping("/buscar")
    public String buscarEndpoint(@ModelAttribute FiltroComunidadVO filtro,
                                 BindingResult results,
                                 Model model) {
        if (results.hasErrors()) {
            // si quieres devolver solo el fragmento de filtros
            return "comunidad/gestionar.html :: filter";
        } else {
            buscar(filtro, model);
            // devolvemos SOLO el fragmento que reemplaza HTMX
            return "comunidad/gestionar.html :: filterAndTable";
        }
    }

    // privates

    private void buscar(FiltroComunidadVO filtro, Model model) {
        var responseMsg = buscarCall(filtro);
        var comunidades = toComunidadVOList(responseMsg);

        var paginated = PaginatedVO.builder()
                .pageNumber(responseMsg.getPageNumber())
                .pageSize(responseMsg.getPageSize())
                .totalPages(responseMsg.getTotalPages())
                .build();

        filtro.setPaginated(paginated);

        model.addAttribute("filtro", filtro);
        model.addAttribute("comunidades", comunidades);
    }

    private BuscarComunidadesResponseMsg buscarCall(FiltroComunidadVO filtro) {
        var requestMsg = BuscarComunidadesRequestMsg.newBuilder()
                .setPageNumber(filtro.getPaginated().getPageNumber())
                .setPageSize(filtro.getPaginated().getPageSize())
                .setDireccion(trimOrBlank(filtro.getDireccion()))
                .setIdComunidad(trimOrBlank(filtro.getId()))
                .setIdComuna(filtro.getComunaId() != null ? filtro.getComunaId() : 0)
                .setIdComunidadTipo(filtro.getTipoId() != null ? filtro.getTipoId() : 0)
                .setIdProvincia(filtro.getProvinciaId() != null ? filtro.getProvinciaId() : 0)
                .setIdRegion(filtro.getRegionId() != null ? filtro.getRegionId() : 0)
                .setNombre(trimOrBlank(filtro.getNombre()))
                .build();
        return comunidadServiceGrpc.buscarComunidades(requestMsg);
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
