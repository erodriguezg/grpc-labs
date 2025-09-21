package com.github.erodriguezg.grpcjavalab.server.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class PersonaDTO {

    private UUID idPersona;

    private Integer rut;

    private String nombres;

    private String apellidos;

    private String email;

    private String telefono1;

    private String telefono2;

    private List<String> rolesComunidad;

}
