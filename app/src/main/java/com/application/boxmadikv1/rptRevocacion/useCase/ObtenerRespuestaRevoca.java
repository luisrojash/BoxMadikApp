package com.application.boxmadikv1.rptRevocacion.useCase;

import com.application.boxmadikv1.api.response.especialista.ObtenerRespuestaRevocaResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.rptRevocacion.dataSource.RespuestaRevocacionDataSource;
import com.application.boxmadikv1.rptRevocacion.dataSource.RespuestaRevocacionRepository;

public class ObtenerRespuestaRevoca extends UseCase<ObtenerRespuestaRevoca.RequestValues, ObtenerRespuestaRevoca.ResponseValue> {

    private RespuestaRevocacionRepository repository;

    public ObtenerRespuestaRevoca(RespuestaRevocacionRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onObtenerRespuestRevocada(requestValues.getPri_codigo(), requestValues.getCodigo_user_crea(), requestValues.getCodigo_user_resp(),
                requestValues.getRevocacion_pais(), new RespuestaRevocacionDataSource.onCallBackResultado<ObtenerRespuestaRevocaResponse>() {
                    @Override
                    public void onResultado(ObtenerRespuestaRevocaResponse resltado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resltado));
                    }
                });
    }


    public static final class RequestValues implements UseCase.RequestValues {
        private String pri_codigo;
        private String codigo_user_crea;
        private String codigo_user_resp;
        private String revocacion_pais;

        public RequestValues(String pri_codigo, String codigo_user_crea, String codigo_user_resp, String revocacion_pais) {
            this.pri_codigo = pri_codigo;
            this.codigo_user_crea = codigo_user_crea;
            this.codigo_user_resp = codigo_user_resp;
            this.revocacion_pais = revocacion_pais;
        }

        public String getPri_codigo() {
            return pri_codigo;
        }

        public String getCodigo_user_crea() {
            return codigo_user_crea;
        }

        public String getCodigo_user_resp() {
            return codigo_user_resp;
        }

        public String getRevocacion_pais() {
            return revocacion_pais;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private ObtenerRespuestaRevocaResponse obtenerRespuestaRevocaResponse;

        public ResponseValue(ObtenerRespuestaRevocaResponse obtenerRespuestaRevocaResponse) {
            this.obtenerRespuestaRevocaResponse = obtenerRespuestaRevocaResponse;
        }

        public ObtenerRespuestaRevocaResponse getObtenerRespuestaRevocaResponse() {
            return obtenerRespuestaRevocaResponse;
        }
    }
}
