package com.application.boxmadikv1.dao.motivoRevocacion;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.MotivoRevocacion;

import java.util.List;

public interface MotivoRevocacionDao extends BaseDao<MotivoRevocacion> {
    /*obtenerListaRevocacion estado activo =1 */

    List<MotivoRevocacion> obtenerListaRevocacion(String estado);
}
