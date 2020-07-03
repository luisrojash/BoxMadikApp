package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource;

import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoDiasUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoPrecioUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoTurnoUi;

import java.util.List;

public interface AtencionDataSource {

    interface CallbackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onListartTipoPrecio(String codigoPais,CallbackResultado<List<TipoRangoPrecioUi>> listCallbackResultado);

    void onListartTipoDias(CallbackResultado<List<TipoRangoDiasUi>> listCallbackResultado);

    void onListartTipoTurno(CallbackResultado<List<TipoRangoTurnoUi>> listCallbackResultado);
}
