package com.github.erodriguezg.grpcjavalab.server.repository.impl;

import com.github.erodriguezg.grpcjavalab.server.dto.FiltroBuscarComunidadDTO;
import com.github.erodriguezg.grpcjavalab.server.entity.Comunidad;
import com.github.erodriguezg.grpcjavalab.server.repository.ComunidadRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ComunidadRepositoryImpl implements ComunidadRepositoryCustom {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ComunidadRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Page<Comunidad> buscarComunidades(FiltroBuscarComunidadDTO filtro) {
        var pageable = PageRequest.of(filtro.getPageNumber(), filtro.getPageSize());
        StringBuilder sql = new StringBuilder("SELECT * FROM comunidades WHERE 1=1");
        StringBuilder countSql = new StringBuilder("SELECT COUNT(*) FROM comunidades WHERE 1=1");
        Map<String, Object> params = new HashMap<>();

        if (filtro.getIdComunidad() != null) {
            sql.append(" AND id_comunidad = :idComunidad");
            countSql.append(" AND id_comunidad = :idComunidad");
            params.put("idComunidad", filtro.getIdComunidad());
        }
        if (filtro.getIdComunidadTipo() != null) {
            sql.append(" AND comunidad_tipo_id = :idComunidadTipo");
            countSql.append(" AND comunidad_tipo_id = :idComunidadTipo");
            params.put("idComunidadTipo", filtro.getIdComunidadTipo());
        }
        if (filtro.getIdComuna() != null) {
            sql.append(" AND comuna_id = :idComuna");
            countSql.append(" AND comuna_id = :idComuna");
            params.put("idComuna", filtro.getIdComuna());
        }
        if (filtro.getNombre() != null) {
            sql.append(" AND nombre ILIKE :nombre");
            countSql.append(" AND nombre ILIKE :nombre");
            params.put("nombre", "%" + filtro.getNombre() + "%");
        }
        if (filtro.getDireccion() != null) {
            sql.append(" AND direccion ILIKE :direccion");
            countSql.append(" AND direccion ILIKE :direccion");
            params.put("direccion", "%" + filtro.getDireccion() + "%");
        }

        // Orden
        sql.append(" ORDER BY nombre ASC");

        // Paginación
        sql.append(" LIMIT :pageLimit OFFSET :pageOffSet");
        params.put("pageLimit", pageable.getPageSize());
        params.put("pageOffSet", pageable.getOffset());

        List<Comunidad> resultados = jdbcTemplate.query(sql.toString(), params, new BeanPropertyRowMapper<>(Comunidad.class));
        Integer total = jdbcTemplate.queryForObject(countSql.toString(), params, Integer.class);

        return new PageImpl<>(resultados, pageable, total != null ? total : 0);
    }
}
