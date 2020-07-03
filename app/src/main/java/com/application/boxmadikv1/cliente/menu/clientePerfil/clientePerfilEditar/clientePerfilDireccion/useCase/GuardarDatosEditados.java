package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.cliente.EditarPerfilResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilDataSource;

import okhttp3.RequestBody;

public class GuardarDatosEditados extends UseCase<GuardarDatosEditados.RequestValues,GuardarDatosEditados.ResponseValue> {

    private ClientePerfilDireccionRepository clientePerfilDireccionRepository;

    public GuardarDatosEditados(ClientePerfilDireccionRepository clientePerfilDireccionRepository) {
        this.clientePerfilDireccionRepository = clientePerfilDireccionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        clientePerfilDireccionRepository.onGuardarDatosEditados(requestValues.getRequestKeyUser(), requestValues.getRequestBodyEstado(), requestValues.getRequestFile(),
                requestValues.getRequestBodyNombre(), requestValues.getRequestBodyApellidos(), requestValues.getRequestBodyCelular(),
                requestValues.getRequestBodyCodigoDepartamento(), requestValues.getRequestBodyCodigoProvincia(), requestValues.getRequestBodyCodigoDistrito(),
                requestValues.getRequestBodyLatitud(), requestValues.getRequestBodyLongitud(), requestValues.getRequestBodyDireccion(), new DatosPerfilDataSource.CallBackResultado<EditarPerfilResponse>() {
                    @Override
                    public void onResultado(EditarPerfilResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        RequestBody requestKeyUser;
        RequestBody requestBodyEstado;
        RequestBody requestFile;
        RequestBody requestBodyNombre;
        RequestBody requestBodyApellidos;
        RequestBody requestBodyCelular;
        RequestBody requestBodyCodigoDepartamento;
        RequestBody requestBodyCodigoProvincia;
        RequestBody requestBodyCodigoDistrito;
        RequestBody requestBodyLatitud;
        RequestBody requestBodyLongitud;
        RequestBody requestBodyDireccion;

        public RequestValues(RequestBody requestKeyUser, RequestBody requestBodyEstado, RequestBody requestFile, RequestBody requestBodyNombre, RequestBody requestBodyApellidos, RequestBody requestBodyCelular, RequestBody requestBodyCodigoDepartamento, RequestBody requestBodyCodigoProvincia, RequestBody requestBodyCodigoDistrito, RequestBody requestBodyLatitud, RequestBody requestBodyLongitud, RequestBody requestBodyDireccion) {
            this.requestKeyUser = requestKeyUser;
            this.requestBodyEstado = requestBodyEstado;
            this.requestFile = requestFile;
            this.requestBodyNombre = requestBodyNombre;
            this.requestBodyApellidos = requestBodyApellidos;
            this.requestBodyCelular = requestBodyCelular;
            this.requestBodyCodigoDepartamento = requestBodyCodigoDepartamento;
            this.requestBodyCodigoProvincia = requestBodyCodigoProvincia;
            this.requestBodyCodigoDistrito = requestBodyCodigoDistrito;
            this.requestBodyLatitud = requestBodyLatitud;
            this.requestBodyLongitud = requestBodyLongitud;
            this.requestBodyDireccion = requestBodyDireccion;
        }

        public RequestBody getRequestKeyUser() {
            return requestKeyUser;
        }

        public RequestBody getRequestBodyEstado() {
            return requestBodyEstado;
        }

        public RequestBody getRequestFile() {
            return requestFile;
        }

        public RequestBody getRequestBodyNombre() {
            return requestBodyNombre;
        }

        public RequestBody getRequestBodyApellidos() {
            return requestBodyApellidos;
        }

        public RequestBody getRequestBodyCelular() {
            return requestBodyCelular;
        }

        public RequestBody getRequestBodyCodigoDepartamento() {
            return requestBodyCodigoDepartamento;
        }

        public RequestBody getRequestBodyCodigoProvincia() {
            return requestBodyCodigoProvincia;
        }

        public RequestBody getRequestBodyCodigoDistrito() {
            return requestBodyCodigoDistrito;
        }

        public RequestBody getRequestBodyLatitud() {
            return requestBodyLatitud;
        }

        public RequestBody getRequestBodyLongitud() {
            return requestBodyLongitud;
        }

        public RequestBody getRequestBodyDireccion() {
            return requestBodyDireccion;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        EditarPerfilResponse defaultResponse;

        public ResponseValue(EditarPerfilResponse defaultResponse) {
            this.defaultResponse = defaultResponse;
        }

        public EditarPerfilResponse getDefaultResponse() {
            return defaultResponse;
        }


    }
}
