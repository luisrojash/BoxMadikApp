package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion.dataSource.CotizacionRepository;

public class EnvioNotificacion extends UseCase<EnvioNotificacion.RequestValues, EnvioNotificacion.ResponseValue> {

    private CotizacionRepository cotizacionRepository;

    public EnvioNotificacion(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        cotizacionRepository.onEnvioNotificacion(requestValues.getGrupoNotificacionCodigo(), requestValues.getTipoNotificacionCodigo(),
                requestValues.getUsuarioCodigo(), requestValues.getPropuestaCodigo(), requestValues.getPaisCodigo(),requestValues.getCodigoCotizacion());

    }

    public static final class RequestValues implements UseCase.RequestValues {
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

    public static final class ResponseValue implements UseCase.ResponseValue {

    }
}
