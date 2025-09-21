package com.github.erodriguezg.grpcjavalab.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("personas")
@Data
public class Persona {

    @Id
    private UUID idPersona;

    private Long rut;

    private String email;

    private String nombres;

    private String apellidos;

    private String telefono1;

    private String telefono2;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;

}
