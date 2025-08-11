package com.github.erodriguezg.grpcjavalab.web.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FiltroComunidadVO {

    private String id;

    private String direccion;

    private String nombre;

    private int tipoId;

    private int comunaId;

    private int provinciaId;

    private int regionId;

    private PaginatedVO paginated;

}
