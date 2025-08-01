package com.github.erodriguezg.grpcjavalab.web.vo;

import lombok.Data;

@Data
public class ComunidadVO {

    private String id;

    private String nombre;

    private String direccion;

    private Integer tipoId;

    private String tipoNombre;

    private Integer comunaId;

    private String comunaNombre;

    private Integer provinciaId;

    private String provinciaNombre;

    private Integer regionId;

    private String regionNombre;

    public String getIdAbreviado() {
        if (id != null && id.trim().length() > 8) {
            return id.substring(0, 8) + "...";
        } else {
            return null;
        }
    }

}
