package com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource.remote.DetallesCotizacionRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public class DetallesCotizacionRepository implements DetallesCotizacionDataSource {

    private DetallesCotizacionRemote detallesCotizacionRemote;
    private static DetallesCotizacionRepository mInstance = null;

    public static DetallesCotizacionRepository getmInstance(DetallesCotizacionRemote detallesCotizacionRemote) {
        if (mInstance == null) {
            mInstance = new DetallesCotizacionRepository(detallesCotizacionRemote);
        }
        return mInstance;
    }

    public DetallesCotizacionRepository(DetallesCotizacionRemote detallesCotizacionRemote) {
        this.detallesCotizacionRemote = detallesCotizacionRemote;
    }

    @Override
    public void onEliminarPropuesta(DetallesCotizacionUi detallesCotizacionU, List<CotizacionesUi> cotizacionesUis, CallBackResultado<DefaultResponse> defaultResponseDetallesCotizacionDataSource) {
        detallesCotizacionRemote.onEliminarPropuesta(detallesCotizacionU, cotizacionesUis, defaultResponseDetallesCotizacionDataSource);
    }

    @Override
    public void onEliminarPropuestaUnica(DetallesCotizacionUi detallesCotizacionU, CallBackResultado<DefaultResponse> defaultResponseDetallesCotizacionDataSource) {
        detallesCotizacionRemote.onEliminarPropuestaUnica(detallesCotizacionU,defaultResponseDetallesCotizacionDataSource);
    }
}
