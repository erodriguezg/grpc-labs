package com.github.erodriguezg.grpcjavalab.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("comunidad_tipos")
@Data
public class ComunidadTipo {

    @Id
    private Integer idComunidadTipo;

    private String nombre;

}
