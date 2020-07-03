package com.application.boxmadikv1.dao.rangoPrecio;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.RangoPrecio;

import java.util.List;

public interface RangoPrecioDao extends BaseDao<RangoPrecio>{
    /*Query Simple - Filtro mismo Id =? */
    RangoPrecio obtenerMiIdQuerySimple(int rangoPrecioId);
    /*Obtener RangoPrecioListapor pais yy por el PAis , Estado Activo = 1*/
    List<RangoPrecio> obtenerListaRangoPrecioPorPais(String codigoPais,String estado);
}
