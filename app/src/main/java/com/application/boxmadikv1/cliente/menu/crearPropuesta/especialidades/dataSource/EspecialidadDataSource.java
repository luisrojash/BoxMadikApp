package com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.dataSource;

import com.application.boxmadikv1.cliente.menu.crearPropuesta.especialidades.entidad.EspecialidadUi;

import java.util.List;

public interface EspecialidadDataSource {

    interface CallbackResultado<T> {
        void onCallbackResultado(T resultado);
    }

    void onListarAutoComplete(int idRubro, int idOficio ,String codigoPais, CallbackResultado<List<EspecialidadUi>> listCallbackResultado);
}
