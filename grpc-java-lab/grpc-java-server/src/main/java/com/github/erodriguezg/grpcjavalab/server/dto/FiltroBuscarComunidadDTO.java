package com.github.erodriguezg.grpcjavalab.server.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FiltroBuscarComunidadDTO {

    private UUID idComunidad;

    private Integer idComunidadTipo;

    private Integer idComuna;

    private Integer idProvincia;

    private Integer idRegion;

    private String nombre;

    private String direccion;

    private int pageSize;

    private int pageNumber;
}
