package com.application.boxmadikv1.dao.provincia;

import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.Provincia;

import java.util.List;

public interface ProvinciaDao extends BaseDao<Provincia> {

    /*Query Simple - Filtro mismo Id =?*/
    Provincia obtenerMiIdQuerySimple(int provinciaId,int paisId,int departamentoId);

    List<Provincia> obtenerProvinciaListaFiltroPaisYdepart(int paisCodigo,int departamentoCodigo);
}
