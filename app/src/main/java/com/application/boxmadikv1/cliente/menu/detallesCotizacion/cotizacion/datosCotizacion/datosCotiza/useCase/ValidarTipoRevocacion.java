package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource.DatosCotizaDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource.DatosCotizaRepository;

public class ValidarTipoRevocacion extends UseCase<ValidarTipoRevocacion.RequestValues,ValidarTipoRevocacion.ResponseValue> {

    private DatosCotizaRepository datosCotizaRepository;

    public ValidarTipoRevocacion(DatosCotizaRepository datosCotizaRepository) {
        this.datosCotizaRepository = datosCotizaRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        datosCotizaRepository.onValidarRevocacion(requestValues.getPais_codigo(), requestValues.getPropuesta_codigo(),
                requestValues.getCodigo_usuario_crea(), requestValues.getEstado_propuesta(),
                new DatosCotizaDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String pais_codigo;
        private String propuesta_codigo;
        private String codigo_usuario_crea;
        private String estado_propuesta;

        public RequestValues(String pais_codigo, String propuesta_codigo, String codigo_usuario_crea, String estado_propuesta) {
            this.pais_codigo = pais_codigo;
            this.propuesta_codigo = propuesta_codigo;
            this.codigo_usuario_crea = codigo_usuario_crea;
            this.estado_propuesta = estado_propuesta;
        }

        public String getPais_codigo() {
            return pais_codigo;
        }

        public String getPropuesta_codigo() {
            return propuesta_codigo;
        }

        public String getCodigo_usuario_crea() {
            return codigo_usuario_crea;
        }

        public String getEstado_propuesta() {
            return estado_propuesta;
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
