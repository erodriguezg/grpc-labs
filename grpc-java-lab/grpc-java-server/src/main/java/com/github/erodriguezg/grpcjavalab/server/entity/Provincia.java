package com.github.erodriguezg.grpcjavalab.server.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("provincias")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Provincia {

    @Id
    private Integer idProvincia;

    private Integer regionId;

    private String nombre;

}
