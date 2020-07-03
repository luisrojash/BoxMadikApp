package com.application.boxmadikv1.registraUser.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.registraUser.dataSource.RegistrarUsuarioDataSource;
import com.application.boxmadikv1.registraUser.dataSource.RegistrarUsuarioRepository;

import okhttp3.RequestBody;


public class RegistrarUsuario extends UseCase<RegistrarUsuario.RequestValues, RegistrarUsuario.ResponseValue> {

    RegistrarUsuarioRepository registrarUsuarioRepository;

    public RegistrarUsuario(RegistrarUsuarioRepository registrarUsuarioRepository) {
        this.registrarUsuarioRepository = registrarUsuarioRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        registrarUsuarioRepository.onRegistrarUsuario(requestValues.getRequestFile(),
                requestValues.getRequestBodyTipoDoc(), requestValues.getRequestBodyNombre(), requestValues.getRequestBodyApellidos(),
                requestValues.getRequestBodyEmail(), requestValues.getRequestBodyClave(), requestValues.getRequestBodyCelular(),
                requestValues.getRequestBodyTipoDocumentoId(), requestValues.getRequestBodyTipoPaisId(), requestValues.getRequestBodyDateTimeCumple(), requestValues.getRequestBodyRazonSocial(), new RegistrarUsuarioDataSource.CallbackResultado<DefaultResponse>() {
                    @Override
                    public void onCallBackResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        RequestBody requestFile;
        RequestBody requestBodyTipoDoc;
        RequestBody requestBodyNombre;
        RequestBody requestBodyApellidos;
        RequestBody requestBodyEmail;
        RequestBody requestBodyClave;
        RequestBody requestBodyCelular;
        RequestBody requestBodyTipoDocumentoId;
        RequestBody requestBodyTipoPaisId;
        RequestBody requestBodyDateTimeCumple;
        RequestBody requestBodyRazonSocial;

        public RequestValues(RequestBody requestFile, RequestBody requestBodyTipoDoc, RequestBody requestBodyNombre, RequestBody requestBodyApellidos, RequestBody requestBodyEmail, RequestBody requestBodyClave, RequestBody requestBodyCelular, RequestBody requestBodyTipoDocumentoId, RequestBody requestBodyTipoPaisId, RequestBody requestBodyDateTimeCumple, RequestBody requestBodyRazonSocial) {
            this.requestFile = requestFile;
            this.requestBodyTipoDoc = requestBodyTipoDoc;
            this.requestBodyNombre = requestBodyNombre;
            this.requestBodyApellidos = requestBodyApellidos;
            this.requestBodyEmail = requestBodyEmail;
            this.requestBodyClave = requestBodyClave;
            this.requestBodyCelular = requestBodyCelular;
            this.requestBodyTipoDocumentoId = requestBodyTipoDocumentoId;
            this.requestBodyTipoPaisId = requestBodyTipoPaisId;
            this.requestBodyDateTimeCumple = requestBodyDateTimeCumple;
            this.requestBodyRazonSocial = requestBodyRazonSocial;
        }

        public RequestBody getRequestFile() {
            return requestFile;
        }

        public RequestBody getRequestBodyTipoDoc() {
            return requestBodyTipoDoc;
        }

        public RequestBody getRequestBodyNombre() {
            return requestBodyNombre;
        }

        public RequestBody getRequestBodyApellidos() {
            return requestBodyApellidos;
        }

        public RequestBody getRequestBodyEmail() {
            return requestBodyEmail;
        }

        public RequestBody getRequestBodyClave() {
            return requestBodyClave;
        }

        public RequestBody getRequestBodyCelular() {
            return requestBodyCelular;
        }

        public RequestBody getRequestBodyTipoDocumentoId() {
            return requestBodyTipoDocumentoId;
        }

        public RequestBody getRequestBodyTipoPaisId() {
            return requestBodyTipoPaisId;
        }

        public RequestBody getRequestBodyDateTimeCumple() {
            return requestBodyDateTimeCumple;
        }

        public RequestBody getRequestBodyRazonSocial() {
            return requestBodyRazonSocial;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        DefaultResponse defaultResponse;

        public ResponseValue(DefaultResponse defaultResponse) {
            this.defaultResponse = defaultResponse;
        }

        public DefaultResponse getDefaultResponse() {
            return defaultResponse;
        }
    }
}
