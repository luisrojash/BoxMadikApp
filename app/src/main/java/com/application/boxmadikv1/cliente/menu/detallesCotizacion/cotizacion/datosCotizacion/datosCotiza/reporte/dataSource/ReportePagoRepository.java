package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource.remote.ReportePagoRemote;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public class ReportePagoRepository implements ReportePagoDataSource {

    private static ReportePagoRepository mInstance = null;
    private ReportePagoRemote reportePagoRemote;

    public static final ReportePagoRepository getInstance(ReportePagoRemote reportePagoRemote) {
        if (mInstance == null) {
            mInstance = new ReportePagoRepository(reportePagoRemote);
        }
        return mInstance;
    }

    public ReportePagoRepository(ReportePagoRemote reportePagoRemote) {
        this.reportePagoRemote = reportePagoRemote;
    }

    @Override
    public void onAceptarCotizacion(DetallesCotizacionUi detallesCotizacionU, CotizacionesUi cotizacionesUi, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        reportePagoRemote.onAceptarCotizacion(detallesCotizacionU, cotizacionesUi, defaultResponseCallBackResultado);
    }

    @Override
    public void onEnviarNotificacionCoti(String notiEstado, String tipoNotiCodigo, String grupoNotiCodigo, String propuestaCodigo, String cotizacionCodigo, String paisCodigo, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        reportePagoRemote.onEnviarNotificacionCoti(notiEstado, tipoNotiCodigo, grupoNotiCodigo, propuestaCodigo, cotizacionCodigo, paisCodigo, defaultResponseCallBackResultado);
    }


}
