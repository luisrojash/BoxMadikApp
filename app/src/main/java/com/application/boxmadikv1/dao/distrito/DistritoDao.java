package com.application.boxmadikv1.dao.distrito;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.Distrito;

import java.util.List;

public interface DistritoDao extends BaseDao<Distrito> {

    /*Query Simple - Filtro mismo Id =?*/
    Distrito obtenerMiIdQuerySimple(int distritoId,int provinciaId,int departamentoId,int paisId);
    /*Quesy Simple -para Obtener Distrito por Departamento*/
    Distrito obtenerMiIdQuerySimplePorDepartamento(int departamentoId,int paisId);

    List<Distrito> obtenerDistritoListaFiltroPaisYDepartUProvi(int paisCodigo, int departamentoCodigo, int provinciaCodigo);
}
