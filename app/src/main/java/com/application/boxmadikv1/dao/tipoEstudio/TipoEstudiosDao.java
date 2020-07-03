package com.application.boxmadikv1.dao.tipoEstudio;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.TipoEstudios;

import java.util.List;

public interface TipoEstudiosDao extends BaseDao<TipoEstudios> {

    List<TipoEstudios> obtenerTipoEstudiosActivo(String estado);

}
