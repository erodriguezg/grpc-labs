package com.github.erodriguezg.grpcjavalab.web.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FiltroComunidadVO {

    private String id;

    private String direccion;

    private String nombre;

    private Integer tipoId;

    private Integer comunaId;

    private Integer provinciaId;

    private Integer regionId;

    private PaginatedVO paginated;

}
