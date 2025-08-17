package com.github.erodriguezg.grpcjavalab.web.form;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GestionarComunidadForm {

    private String id;

    private String direccion;

    private String nombre;

    private Integer tipoId;

    private Integer comunaId;

    private Integer provinciaId;

    private Integer regionId;

    private PaginatedForm paginated;

    private boolean verFiltros;

}
