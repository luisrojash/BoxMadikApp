package com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface DetallesCotizacionDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onEliminarPropuesta(DetallesCotizacionUi detallesCotizacionU, List<CotizacionesUi> cotizacionesUis, DetallesCotizacionDataSource.CallBackResultado<DefaultResponse> defaultResponseDetallesCotizacionDataSource);

    void onEliminarPropuestaUnica(DetallesCotizacionUi detallesCotizacionU, DetallesCotizacionDataSource.CallBackResultado<DefaultResponse> defaultResponseDetallesCotizacionDataSource);

}
