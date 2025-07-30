package com.github.erodriguezg.grpcjavalab.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("provincias")
@Data
public class Provincia {

    @Id
    private Integer idProvincia;

    private Integer regionId;

    private String nombre;

}
