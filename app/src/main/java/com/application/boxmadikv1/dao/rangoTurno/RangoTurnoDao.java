package com.application.boxmadikv1.dao.rangoTurno;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.RangoTurno;

import java.util.List;

public interface RangoTurnoDao extends BaseDao<RangoTurno> {

    /*Query Simple - Filtro mismo Id =?*/
    RangoTurno obtenerMiIdQuerySimple(int rangoTurnoId);

    List<RangoTurno> obtenerListaRangoTurnoPorEsado(String estado);
}
