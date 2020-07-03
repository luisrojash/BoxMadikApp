package com.application.boxmadikv1.dao.especialidades;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.Especialidades;

import java.util.List;

public interface EspecialidadesDao extends BaseDao<Especialidades> {

    /*Listado Especialidades - Por filtro RubroId  y OficioId*/
    List<Especialidades> obtenerListaPorRubroIdAndOficioId(String rubroId,String oficioId,String codigoPais,String estado );
}
