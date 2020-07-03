package com.application.boxmadikv1.dao.rubro;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.Rubro;

import java.util.List;

public interface RubroDao extends BaseDao<Rubro> {

    /*Query Simple - Filtro mismo Id =?*/
    Rubro obtenerMiIdQuerySimple(int rubroId);
    /*Obtener Lista Rubro por Pais y Estado Activo = 1*/
    List<Rubro> obtenerListaRubroPorPais(String paisCodigo,String rubroEstado);
}
