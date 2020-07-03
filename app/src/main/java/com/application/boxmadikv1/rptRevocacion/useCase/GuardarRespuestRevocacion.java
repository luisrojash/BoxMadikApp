package com.application.boxmadikv1.rptRevocacion.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.rptRevocacion.dataSource.RespuestaRevocacionDataSource;
import com.application.boxmadikv1.rptRevocacion.dataSource.RespuestaRevocacionRepository;

public class GuardarRespuestRevocacion extends UseCase<GuardarRespuestRevocacion.RequestValues, GuardarRespuestRevocacion.ResponseValue> {

    private RespuestaRevocacionRepository respuestaRevocacionRepository;

    public GuardarRespuestRevocacion(RespuestaRevocacionRepository respuestaRevocacionRepository) {
        this.respuestaRevocacionRepository = respuestaRevocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        respuestaRevocacionRepository.onGuardarRegistroRevocacion(requestValues.getTipoRespuesta(),
                requestValues.getPropuestRevocacionId(),
                requestValues.getDetalleRespuesta(),
                requestValues.getPropuestaId(),
                requestValues.getCodigoUsuarioPropuesta(),
                requestValues.getCodigoUsuarioCotizacion(),
                new RespuestaRevocacionDataSource.onCallBackResultado<DefaultResponse>() {
                    @Override
                    public void onResultado(DefaultResponse resltado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resltado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String tipoRespuesta;
        private String propuestRevocacionId;
        private String detalleRespuesta;
        private String propuestaId;
        private String codigoUsuarioPropuesta;
        private String codigoUsuarioCotizacion;

        public RequestValues(String tipoRespuesta, String propuestRevocacionId, String detalleRespuesta, String propuestaId, String codigoUsuarioPropuesta, String codigoUsuarioCotizacion) {
            this.tipoRespuesta = tipoRespuesta;
            this.propuestRevocacionId = propuestRevocacionId;
            this.detalleRespuesta = detalleRespuesta;
            this.propuestaId = propuestaId;
            this.codigoUsuarioPropuesta = codigoUsuarioPropuesta;
            this.codigoUsuarioCotizacion = codigoUsuarioCotizacion;
        }

        public String getTipoRespuesta() {
            return tipoRespuesta;
        }

        public String getPropuestRevocacionId() {
            return propuestRevocacionId;
        }

        public String getDetalleRespuesta() {
            return detalleRespuesta;
        }

        public String getPropuestaId() {
            return propuestaId;
        }

        public String getCodigoUsuarioPropuesta() {
            return codigoUsuarioPropuesta;
        }

        public String getCodigoUsuarioCotizacion() {
            return codigoUsuarioCotizacion;
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
    }
}
