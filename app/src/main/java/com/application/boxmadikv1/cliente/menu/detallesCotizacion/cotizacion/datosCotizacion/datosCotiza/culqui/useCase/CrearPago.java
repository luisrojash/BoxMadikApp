package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.useCase;

import com.application.boxmadikv1.api.culqui.CargoResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source.CulquiDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui.source.CulquiRepository;

import org.json.JSONObject;

import retrofit2.Response;

public class CrearPago extends UseCase<CrearPago.RequestValues, CrearPago.ResponseValue> {

    private CulquiRepository repository;

    public CrearPago(CulquiRepository repository) {
        this.repository = repository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        repository.onCrearPago(requestValues.getJsonBody(), new CulquiDataSource.CallBackResultado<Response<CargoResponse>, Integer>() {
            @Override
            public void onCallBakResultado(Response<CargoResponse> resultado, Integer resultadoResponse) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado,resultadoResponse));
                /*if (resultado != null) {
                    getUseCaseCallback().onSuccess(new ResponseValue(resultado,resultadoResponse));
                } else {
                    getUseCaseCallback().onError();
                }*/
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
        private Response<CargoResponse> response;
        private int errorResponse;

        public ResponseValue(Response<CargoResponse> response, int errorResponse) {
            this.response = response;
            this.errorResponse = errorResponse;
        }

        public Response<CargoResponse> getResponse() {
            return response;
        }

        public int getErrorResponse() {
            return errorResponse;
        }
    }
}
