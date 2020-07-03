package com.application.boxmadikv1.dao.tipoCentroEstudios;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.CentroEstudios;

import java.util.List;

public interface TipoCentroEstudiosDao extends BaseDao<CentroEstudios> {
    List<CentroEstudios> obtenerCentroEstudiosActivo(String paisCodigo, String estado);
}
