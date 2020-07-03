package com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.dataSource;

import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.descripcion.dataSource.remote.DetallesDescripcionRemote;

public class DetallesDescripcionRepository implements DetallesDescripcionDataSource {


    private static DetallesDescripcionRepository mInstance = null;

    public static final DetallesDescripcionRepository getmInstance(DetallesDescripcionRemote detallesDescripcionRemote) {
        if (mInstance == null) {
            mInstance = new DetallesDescripcionRepository(detallesDescripcionRemote);
        }
        return mInstance;
    }

    private DetallesDescripcionRemote detallesDescripcionRemote;


    public DetallesDescripcionRepository(DetallesDescripcionRemote detallesDescripcionRemote) {
        this.detallesDescripcionRemote = detallesDescripcionRemote;
    }

    @Override
    public void mostrarImagenPropuesta(String codigoPropuesta, CallBackResultado<MostrarImagenResponse> mostrarImagenResponseCallBackResultado) {
        detallesDescripcionRemote.mostrarImagenPropuesta(codigoPropuesta,mostrarImagenResponseCallBackResultado);
    }

}
