package com.application.boxmadikv1.cliente.menu.crearPropuesta.dataSource;

import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;

import java.util.List;

public interface RubrosDataSource {

    interface CallBackResultado<T> {
        void onCalbackResultado(T resultado);
    }

    void onListarRubros(String codigoPais,CallBackResultado<List<RubrosUi>> listCallBackResultado);
}
