package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource.remote.DesembolsarRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public class DesembolsarRepository implements DesembolsarDataSource {

    private static DesembolsarRepository mInstance = null;
    private DesembolsarRemote desembolsarRemote;

    public DesembolsarRepository(DesembolsarRemote desembolsarRemote) {
        this.desembolsarRemote = desembolsarRemote;
    }

    public static final DesembolsarRepository getmInstance(DesembolsarRemote desembolsarRemote) {
        if (mInstance == null) {
            mInstance = new DesembolsarRepository(desembolsarRemote);
        }
        return mInstance;
    }

    @Override
    public void onRegistrarDesembolso(String ratingValue, String editTextComentario, DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        desembolsarRemote.onRegistrarDesembolso(ratingValue, editTextComentario, detallesCotizacionUi, cotizacionesUi, defaultResponseCallBackResultado);
    }

    @Override
    public void onCambiarEstadoFinalizado(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        desembolsarRemote.onCambiarEstadoFinalizado(detallesCotizacionUi, cotizacionesUi, defaultResponseCallBackResultado);
    }
}
