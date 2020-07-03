package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionRepository;

public class EnvioNotificacionRevocacion extends UseCase<EnvioNotificacionRevocacion.RequestValues,EnvioNotificacionRevocacion.ResponseValue> {

    private RevocacionRepository revocacionRepository;

    public EnvioNotificacionRevocacion(RevocacionRepository revocacionRepository) {
        this.revocacionRepository = revocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        revocacionRepository.onEnvioNotificacion(requestValues.getGrupoNotificacionCodigo(), requestValues.getTipoNotificacionCodigo(), requestValues.getUsuarioCodigo(), requestValues.getPropuestaCodigo(), requestValues.getPaisCodigo(), requestValues.getCodigoCotizacion(), new RevocacionDataSource.CallBackResultado<DefaultResponse>() {
            @Override
            public void onResultado(DefaultResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String grupoNotificacionCodigo;
        private String tipoNotificacionCodigo;
        private String usuarioCodigo;
        private String propuestaCodigo;
        private String paisCodigo;
        private String codigoCotizacion;

        public RequestValues(String grupoNotificacionCodigo, String tipoNotificacionCodigo,
                             String usuarioCodigo, String propuestaCodigo, String paisCodigo,
                             String codigoCotizacion) {
            this.grupoNotificacionCodigo = grupoNotificacionCodigo;
            this.tipoNotificacionCodigo = tipoNotificacionCodigo;
            this.usuarioCodigo = usuarioCodigo;
            this.propuestaCodigo = propuestaCodigo;
            this.paisCodigo = paisCodigo;
            this.codigoCotizacion = codigoCotizacion;
        }

        public String getGrupoNotificacionCodigo() {
            return grupoNotificacionCodigo;
        }

        public String getTipoNotificacionCodigo() {
            return tipoNotificacionCodigo;
        }

        public String getUsuarioCodigo() {
            return usuarioCodigo;
        }

        public String getPropuestaCodigo() {
            return propuestaCodigo;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }

        public String getCodigoCotizacion() {
            return codigoCotizacion;
        }
    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        DefaultResponse defaultResponse;

        public ResponseValue(DefaultResponse defaultResponse) {
            this.defaultResponse = defaultResponse;
        }

        public DefaultResponse getDefaultResponse() {
            return defaultResponse;
        }
    }
}
