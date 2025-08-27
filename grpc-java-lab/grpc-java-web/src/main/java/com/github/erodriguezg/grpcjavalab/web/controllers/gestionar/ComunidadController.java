package com.github.erodriguezg.grpcjavalab.web.controllers.gestionar;

import com.github.erodriguezg.grpcjavalab.api.grpc.*;
import com.github.erodriguezg.grpcjavalab.web.form.GestionarComunidadForm;
import com.github.erodriguezg.grpcjavalab.web.form.PaginatedForm;
import com.github.erodriguezg.grpcjavalab.web.view.ComunidadView;
import com.github.erodriguezg.grpcjavalab.web.view.ValueLabel;
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
import java.util.Collections;
import java.util.List;

import static com.github.erodriguezg.grpcjavalab.web.utils.StringUtil.trimOrBlank;

@Controller
@RequestMapping("/gestionar/comunidad")
@RequiredArgsConstructor
public class ComunidadController {

    private final static Logger log = LoggerFactory.getLogger(ComunidadController.class);

    private static final int PAGE_SIZE_COMUNIDADES = 10;

    private final ComunidadServiceGrpc.ComunidadServiceBlockingStub comunidadServiceGrpc;

    private final TerritorioServiceGrpc.TerritorioServiceBlockingStub territorioServiceGrpc;

    @GetMapping
    public String irGestionar(Model model) {
        var paginated = PaginatedForm.builder()
                .pageNumber(0)
                .pageSize(PAGE_SIZE_COMUNIDADES)
                .build();

        var form = GestionarComunidadForm.builder()
                .paginated(paginated)
                .verFiltros(false)
                .build();

        buscar(form, model);
        return "comunidad/gestionar.html"; // vista completa en la primera carga
    }

    @PostMapping("/buscar")
    public String buscarEndpoint(@ModelAttribute GestionarComunidadForm form,
                                 BindingResult results,
                                 Model model) {
        if (results.hasErrors()) {
            // si quieres devolver solo el fragmento de filtros
            return "comunidad/gestionar.html :: filter";
        } else {
            buscar(form, model);
            // devolvemos SOLO el fragmento que reemplaza HTMX
            return "comunidad/gestionar.html :: filterAndTable";
        }
    }

    // privates

    private void traerRegionProvinciasComunas(GestionarComunidadForm form, Model model) {
        traerRegiones(model);
        traerProvincias(form, model);
        traerComunas(form, model);
    }

    private void traerRegiones(Model model) {
        var requestMsg = TraerRegionesRequestMsg.newBuilder()
                .build();
        var responseMsg = territorioServiceGrpc.traerRegiones(requestMsg);
        var regiones = responseMsg.getRegionesList()
                .stream()
                .map(msg ->
                        ValueLabel.<Integer>builder()
                                .value(msg.getIdRegion())
                                .label(msg.getNombre())
                                .build())
                .toList();
        model.addAttribute("regiones", regiones);
    }

    private void traerProvincias(GestionarComunidadForm form, Model model) {
        List<ValueLabel<Integer>> provincias;
        if (form.getRegionId() != null) {
            var requestMsg = TraerProvinciasRequestMsg.newBuilder()
                    .setRegionId(form.getRegionId())
                    .build();
            var responseMsg = territorioServiceGrpc.traerProvinciasPorRegion(requestMsg);
            provincias = responseMsg.getProvinciasList()
                    .stream()
                    .map(msg ->
                            ValueLabel.<Integer>builder()
                                    .value(msg.getIdProvincia())
                                    .label(msg.getNombre())
                                    .build())
                    .toList();
        } else {
            provincias = Collections.emptyList();
        }
        model.addAttribute("provincias", provincias);
    }

    private void traerComunas(GestionarComunidadForm form, Model model) {
        List<ValueLabel<Integer>> comunas;
        if (form.getProvinciaId() != null) {
            var requestMsg = TraerComunasRequestMsg.newBuilder()
                    .setProvinciaId(form.getProvinciaId())
                    .build();
            var responseMsg = territorioServiceGrpc.traerComunasPorProvincia(requestMsg);
            comunas = responseMsg.getComunasList()
                    .stream()
                    .map(msg ->
                            ValueLabel.<Integer>builder()
                                    .value(msg.getIdComuna())
                                    .label(msg.getNombre())
                                    .build())
                    .toList();
        } else {
            comunas = Collections.emptyList();
        }
        model.addAttribute("comunas", comunas);
    }

    private void buscar(GestionarComunidadForm form, Model model) {

        traerRegionProvinciasComunas(form, model);

        var responseMsg = buscarCall(form);
        var comunidades = toComunidadViewList(responseMsg);

        var paginated = PaginatedForm.builder()
                .pageNumber(responseMsg.getPageNumber())
                .pageSize(responseMsg.getPageSize())
                .totalPages(responseMsg.getTotalPages())
                .build();

        form.setPaginated(paginated);

        model.addAttribute("form", form);
        model.addAttribute("comunidades", comunidades);
    }

    private BuscarComunidadesResponseMsg buscarCall(GestionarComunidadForm form) {
        var requestMsg = BuscarComunidadesRequestMsg.newBuilder()
                .setPageNumber(form.getPaginated().getPageNumber())
                .setPageSize(form.getPaginated().getPageSize())
                .setDireccion(trimOrBlank(form.getDireccion()))
                .setIdComunidad(trimOrBlank(form.getId()))
                .setIdComuna(form.getComunaId() != null ? form.getComunaId() : 0)
                .setIdComunidadTipo(form.getTipoId() != null ? form.getTipoId() : 0)
                .setIdProvincia(form.getProvinciaId() != null ? form.getProvinciaId() : 0)
                .setIdRegion(form.getRegionId() != null ? form.getRegionId() : 0)
                .setNombre(trimOrBlank(form.getNombre()))
                .build();
        return comunidadServiceGrpc.buscarComunidades(requestMsg);
    }

    private List<ComunidadView> toComunidadViewList(BuscarComunidadesResponseMsg responseMsg) {
        var comunidades = new ArrayList<ComunidadView>();
        responseMsg.getItemsList().forEach(msg -> {
            var view = new ComunidadView();
            view.setComunaId(msg.getComunaId());
            view.setComunaNombre(msg.getComunaNombre());
            view.setDireccion(msg.getDireccion());
            view.setId(msg.getId());
            view.setNombre(msg.getNombre());
            view.setProvinciaId(msg.getProvinciaId());
            view.setProvinciaNombre(msg.getProvinciaNombre());
            view.setRegionId(msg.getRegionId());
            view.setRegionNombre(msg.getRegionNombre());
            view.setTipoId(msg.getTipoId());
            view.setTipoNombre(msg.getTipoNombre());
            comunidades.add(view);
        });
        return comunidades;
    }

}
