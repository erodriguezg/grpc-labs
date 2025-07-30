package com.github.erodriguezg.grpcjavalab.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("comunas")
@Data
public class Comuna {

    @Id
    private Integer idComuna;

    private Integer provinciaId;

    private String nombre;

}
