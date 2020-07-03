package com.application.boxmadikv1.dao.departamento;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.Departamento;

import java.util.List;

public interface DepartamentoDao extends BaseDao<Departamento> {

    /*Query Simple - Filtro mismo Id =?*/
    Departamento obtenerMiIdQuerySimple(int departamentoId, int paisId);

    /*Filtro por Pais */

    List<Departamento> departamentoPorPais(int paisCodigo);
}
