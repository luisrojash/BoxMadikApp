package com.application.boxmadikv1.dao.rangoDias;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.RangoDias;

import java.util.List;

public interface RangoDiasDao extends BaseDao<RangoDias> {

    /*Query Simple - Filtro mismo Id =?*/
    RangoDias obtenerMiIdQuerySimple(int rangoDiasId);

    /*obtenerLista - Filtro EstadoActivo*/
    List<RangoDias> obtenerListaRangoDiasEstado(String estado);

}
