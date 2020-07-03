package com.application.boxmadikv1.dao.calidadTrabajo;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.CalidadTrabajo;

import java.util.List;

public interface CalidadTrabajoDao extends BaseDao<CalidadTrabajo> {
    List<CalidadTrabajo> obtenerListaCalidadaTrabajoActivo(String activo);
}
