package com.github.erodriguezg.grpcjavalab.server.repository.impl;

import com.github.erodriguezg.grpcjavalab.server.dto.ComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.repository.ComunidadRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ComunidadRepositoryImpl implements ComunidadRepositoryCustom {


    private static final String BUSCAR_COMUNIDADES_QUERY_PROJECTION =
            """
            select
                co.id_comunidad as id,
                co.nombre as nombre,
                co.direccion as direccion,
                ct.id_comunidad_tipo as tipo_id,
                ct.nombre as tipo_nombre,
                ca.id_comuna as comuna_id,
                ca.nombre as comuna_nombre,
                pr.id_provincia as provincia_id,
                pr.nombre as provincia_nombre,
                re.id_region as region_id,
                re.nombre as region_nombre
            """;

    private static final String BUSCAR_COMUNIDADES_QUERY_PROJECTION_COUNT = "select count (*) ";

    private static final String BUSCAR_COMUNIDADES_QUERY_BODY =
            """
             from comunidades co
            join comunidad_tipos ct on (
                ct.id_comunidad_tipo = co.comunidad_tipo_id
            )
            join comunas ca on (
                ca.id_comuna = co.comuna_id
            )
            join provincias pr on (
                pr.id_provincia = ca.provincia_id
            )
            join regiones re on (
                re.id_region = pr.region_id
            )
            WHERE 1 = 1
            """;

    private static final String BUSCAR_COMUNIDADES_QUERY_ORDEN = " order by co.nombre asc";

    private static final String BUSCAR_COMUNIDADES_QUERY_PAGINACION = " limit :pageLimit OFFSET :pageOffSet";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ComunidadRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Page<ComunidadDTO> buscarComunidades(FiltroBuscarComunidadDTO filtro) {
        var pageable = PageRequest.of(filtro.getPageNumber(), filtro.getPageSize());
        StringBuilder sqlConditions = new StringBuilder();
        Map<String, Object> params = new HashMap<>();

        if (filtro.getIdComunidad() != null) {
            sqlConditions.append(" and co.id_comunidad = :idComunidad");
            params.put("idComunidad", filtro.getIdComunidad());
        }
        if (filtro.getIdComunidadTipo() != null) {
            sqlConditions.append(" and ct.id_comunidad_tipo = :idComunidadTipo");
            params.put("idComunidadTipo", filtro.getIdComunidadTipo());
        }
        if (filtro.getIdComuna() != null) {
            sqlConditions.append(" and ca.id_comuna = :idComuna");
            params.put("idComuna", filtro.getIdComuna());
        }
        if (filtro.getIdProvincia() != null) {
            sqlConditions.append(" and pr.id_provincia = :idProvincia");
            params.put("idProvincia", filtro.getIdProvincia());
        }
        if (filtro.getIdRegion() != null) {
            sqlConditions.append(" and re.id_region = :idRegion");
            params.put("idRegion", filtro.getIdRegion());
        }
        if (filtro.getNombre() != null && !filtro.getNombre().isBlank()) {
            sqlConditions.append(" and co.nombre ILIKE :nombre");
            params.put("nombre", "%" + filtro.getNombre() + "%");
        }
        if (filtro.getDireccion() != null && !filtro.getDireccion().isBlank()) {
            sqlConditions.append(" and co.direccion ILIKE :direccion");
            params.put("direccion", "%" + filtro.getDireccion() + "%");
        }

        // paginacion params
        params.put("pageLimit", pageable.getPageSize());
        params.put("pageOffSet", pageable.getOffset());

        var query = BUSCAR_COMUNIDADES_QUERY_PROJECTION +
                BUSCAR_COMUNIDADES_QUERY_BODY +
                sqlConditions.toString() +
                BUSCAR_COMUNIDADES_QUERY_ORDEN +
                BUSCAR_COMUNIDADES_QUERY_PAGINACION;

        var queryCount = BUSCAR_COMUNIDADES_QUERY_PROJECTION_COUNT +
                BUSCAR_COMUNIDADES_QUERY_BODY +
                sqlConditions.toString();

        List<ComunidadDTO> resultados = jdbcTemplate.query(query, params, new BeanPropertyRowMapper<>(ComunidadDTO.class));
        Integer total = jdbcTemplate.queryForObject(queryCount, params, Integer.class);
        if (total == null) {
            total = 0;
        }

        return new PageImpl<>(resultados, pageable, total);
    }
}
