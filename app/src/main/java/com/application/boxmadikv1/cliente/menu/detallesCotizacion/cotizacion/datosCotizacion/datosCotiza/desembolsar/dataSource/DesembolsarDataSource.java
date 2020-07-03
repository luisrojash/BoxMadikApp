package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface DesembolsarDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onRegistrarDesembolso(String ratingValue, String editTextComentario,
                               DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi,
                               DesembolsarDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);

    void onCambiarEstadoFinalizado(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi,
                                   DesembolsarDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);

}
