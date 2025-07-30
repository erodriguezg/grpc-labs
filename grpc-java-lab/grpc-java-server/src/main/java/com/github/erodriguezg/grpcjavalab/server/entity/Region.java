package com.github.erodriguezg.grpcjavalab.server.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("regiones")
@Data
public class Region {

    @Id
    private Integer idRegion;

    private String nombre;

}
