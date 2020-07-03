package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilDataSource;

public class ObtenerPais extends UseCase<ObtenerPais.RequestValues, ObtenerPais.ResponseValue> {

    private DatosPerfilDataSource datosPerfilDataSource;

    public ObtenerPais(DatosPerfilDataSource datosPerfilDataSource) {
        this.datosPerfilDataSource = datosPerfilDataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        datosPerfilDataSource.onObtenerNombrePais(requestValues.getKeyPais(), new DatosPerfilDataSource.CallBackResultado<String>() {
            @Override
            public void onResultado(String resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String keyPais;

        public RequestValues(String keyPais) {
            this.keyPais = keyPais;
        }

        public String getKeyPais() {
            return keyPais;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private String paisNombre;

        public ResponseValue(String paisNombre) {
            this.paisNombre = paisNombre;
        }

        public String getPaisNombre() {
            return paisNombre;
        }
    }
}
