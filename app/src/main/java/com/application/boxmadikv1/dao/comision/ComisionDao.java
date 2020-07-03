package com.application.boxmadikv1.dao.comision;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.BoxMadik_Comision;

public interface ComisionDao extends BaseDao<BoxMadik_Comision> {
    /*Query Simple - Filtro mismo Id =?*/
    BoxMadik_Comision obtenerMiIdQuerySimple(int comisionId);
}
