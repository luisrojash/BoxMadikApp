package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource;

import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.local.AtencionLocal;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoDiasUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoPrecioUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoTurnoUi;

import java.util.List;

public class AtencionRepository implements AtencionDataSource {
    private AtencionLocal atencionLocal;
    private static AtencionRepository mInstance = null;

    public static final AtencionRepository getmInstance(AtencionLocal atencionLocal) {
        if (mInstance == null) {
            mInstance = new AtencionRepository(atencionLocal);
        }
        return mInstance;
    }

    public AtencionRepository(AtencionLocal atencionLocal) {
        this.atencionLocal = atencionLocal;
    }


    @Override
    public void onListartTipoPrecio(String codigoPais, CallbackResultado<List<TipoRangoPrecioUi>> listCallbackResultado) {
        atencionLocal.onListartTipoPrecio(codigoPais,listCallbackResultado);
    }

    @Override
    public void onListartTipoDias(CallbackResultado<List<TipoRangoDiasUi>> listCallbackResultado) {
        atencionLocal.onListartTipoDias(listCallbackResultado);
    }

    @Override
    public void onListartTipoTurno(CallbackResultado<List<TipoRangoTurnoUi>> listCallbackResultado) {
        atencionLocal.onListartTipoTurno(listCallbackResultado);
    }
}
