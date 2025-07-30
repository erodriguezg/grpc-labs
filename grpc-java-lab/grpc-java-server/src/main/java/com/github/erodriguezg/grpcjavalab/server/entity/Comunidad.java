package com.github.erodriguezg.grpcjavalab.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Table("comunidades")
@Data
public class Comunidad {

    @Id
    private UUID idComunidad;

    private Integer comunidadTipoId;

    private Integer comunaId;

    private String nombre;

    private String direccion;

    private LocalDateTime fechaCreacion;

    private LocalDateTime fechaModificacion;

}
