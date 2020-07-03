package com.application.boxmadikv1.dao.pais;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.Pais;

import java.util.List;

public interface PaisDao extends BaseDao<Pais> {

    Pais obtenerPais(int paisCodigo);

    /*Obtener Lista de Paises Estado Activo = 1*/
    List<Pais>obtenerListaEstado(String estado);
}
