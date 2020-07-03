package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface ReportePagoDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onAceptarCotizacion(DetallesCotizacionUi detallesCotizacionU,
                             CotizacionesUi cotizacionesUi, ReportePagoDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);

    void onEnviarNotificacionCoti(String notiEstado,String tipoNotiCodigo,String grupoNotiCodigo,String propuestaCodigo,String cotizacionCodigo,String paisCodigo, ReportePagoDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);


}
