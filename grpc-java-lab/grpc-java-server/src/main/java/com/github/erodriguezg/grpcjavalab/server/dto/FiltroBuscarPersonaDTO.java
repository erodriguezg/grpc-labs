package com.github.erodriguezg.grpcjavalab.server.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class FiltroBuscarPersonaDTO {

    private UUID idComunidad;

    private UUID idPersona;

    private Long rut;

    private String email;

    private String nombres;

    private String apellidos;

    private int pageSize;

    private int pageNumber;
}
