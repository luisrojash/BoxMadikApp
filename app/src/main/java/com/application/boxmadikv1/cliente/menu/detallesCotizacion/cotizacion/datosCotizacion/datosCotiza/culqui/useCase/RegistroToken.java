package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.useCase;

import com.application.boxmadikv1.api.culqui.IntegracionResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source.CulquiDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source.CulquiRepository;

import org.json.JSONObject;

public class RegistroToken extends UseCase<RegistroToken.RequestValues, RegistroToken.ResponseValue> {

    private CulquiRepository repository;

    public RegistroToken(CulquiRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onCrearTokenUsuario(requestValues.getJsonBody(), new CulquiDataSource.CallBackResultado<IntegracionResponse, Integer>() {
            @Override
            public void onCallBakResultado(IntegracionResponse resultado, Integer resultadoResponse) {
                if (resultado != null) {
                    getUseCaseCallback().onSuccess(new ResponseValue(resultado,resultadoResponse));
                } else {
                    getUseCaseCallback().onError();
                }
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private JSONObject jsonBody;

        public RequestValues(JSONObject jsonBody) {
            this.jsonBody = jsonBody;
        }

        public JSONObject getJsonBody() {
            return jsonBody;
        }

        public void setJsonBody(JSONObject jsonBody) {
            this.jsonBody = jsonBody;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private IntegracionResponse serverResponse;
        private int erroResponse;

        public ResponseValue(IntegracionResponse serverResponse, int erroResponse) {
            this.serverResponse = serverResponse;
            this.erroResponse = erroResponse;
        }

        public IntegracionResponse getServerResponse() {
            return serverResponse;
        }

        public int getErroResponse() {
            return erroResponse;
        }
    }
}
