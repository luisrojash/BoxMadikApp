package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource.ReportePagoDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource.ReportePagoRepository;

public class NotificacionCotiAceptada extends UseCase<NotificacionCotiAceptada.RequestValues, NotificacionCotiAceptada.ResponseValue> {

    private ReportePagoRepository reportePagoRepository;

    public NotificacionCotiAceptada(ReportePagoRepository reportePagoRepository) {
        this.reportePagoRepository = reportePagoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        reportePagoRepository.onEnviarNotificacionCoti(requestValues.getNotiEstado(), requestValues.getTipoNotiCodigo(), requestValues.getGrupoNotiCodigo()
                , requestValues.getPropuestaCodigo(), requestValues.getCotizacionCodigo(), requestValues.getPaisCodigo(), new ReportePagoDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onCallBackResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String notiEstado;
        private String tipoNotiCodigo;
        private String grupoNotiCodigo;
        private String propuestaCodigo;
        private String cotizacionCodigo;
        private String paisCodigo;

        public RequestValues(String notiEstado, String tipoNotiCodigo, String grupoNotiCodigo, String propuestaCodigo, String cotizacionCodigo, String paisCodigo) {
            this.notiEstado = notiEstado;
            this.tipoNotiCodigo = tipoNotiCodigo;
            this.grupoNotiCodigo = grupoNotiCodigo;
            this.propuestaCodigo = propuestaCodigo;
            this.cotizacionCodigo = cotizacionCodigo;
            this.paisCodigo = paisCodigo;
        }

        public String getNotiEstado() {
            return notiEstado;
        }

        public void setNotiEstado(String notiEstado) {
            this.notiEstado = notiEstado;
        }

        public String getTipoNotiCodigo() {
            return tipoNotiCodigo;
        }

        public void setTipoNotiCodigo(String tipoNotiCodigo) {
            this.tipoNotiCodigo = tipoNotiCodigo;
        }

        public String getGrupoNotiCodigo() {
            return grupoNotiCodigo;
        }

        public void setGrupoNotiCodigo(String grupoNotiCodigo) {
            this.grupoNotiCodigo = grupoNotiCodigo;
        }

        public String getPropuestaCodigo() {
            return propuestaCodigo;
        }

        public void setPropuestaCodigo(String propuestaCodigo) {
            this.propuestaCodigo = propuestaCodigo;
        }

        public String getCotizacionCodigo() {
            return cotizacionCodigo;
        }

        public void setCotizacionCodigo(String cotizacionCodigo) {
            this.cotizacionCodigo = cotizacionCodigo;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }

        public void setPaisCodigo(String paisCodigo) {
            this.paisCodigo = paisCodigo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private DefaultResponse defaultResponse;

        public ResponseValue(DefaultResponse defaultResponse) {
            this.defaultResponse = defaultResponse;
        }

        public DefaultResponse getDefaultResponse() {
            return defaultResponse;
        }

        public void setDefaultResponse(DefaultResponse defaultResponse) {
            this.defaultResponse = defaultResponse;
        }
    }
}
