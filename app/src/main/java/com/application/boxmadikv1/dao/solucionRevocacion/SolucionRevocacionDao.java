package com.application.boxmadikv1.dao.solucionRevocacion;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.SolucionRevocacion;

import java.util.List;

public interface SolucionRevocacionDao extends BaseDao<SolucionRevocacion> {

    List<SolucionRevocacion> obtenerListaRevocacionActiva(String estado);
}
