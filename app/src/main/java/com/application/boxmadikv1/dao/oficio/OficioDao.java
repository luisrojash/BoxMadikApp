package com.application.boxmadikv1.dao.oficio;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.Oficio;

import java.util.List;

public interface OficioDao extends BaseDao<Oficio> {

    /*Query Simple - Filtro mismo Id =?*/
    Oficio obtenerMiIdQuerySimple(int oficioId);

    /*Listado Oficio por filtro rubro_Rub_Codigo = ? , codigoPais*/
    List<Oficio> obtenerListaOficioPorRubroCodigo(String rubro_Rub_Codigo,String codigoPais,String oficioEstado);
}
