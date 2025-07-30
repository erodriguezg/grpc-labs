package com.github.erodriguezg.grpcjavalab.server.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ComunidadDTO {

    private UUID id;

    private String nombre;

    private String direccion;

    private Integer tipoId;

    private String tipoNombre;

    private Integer comunaId;

    private String comunaNombre;

    private Integer provinciaId;

    private String provinciaNombre;

    private Integer regionId;

    private String regionNombre;

}
