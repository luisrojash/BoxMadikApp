package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.dataSource.DatosPerfilDataSource;

public class ObtenerTipoDoc extends UseCase<ObtenerTipoDoc.RequestValues,ObtenerTipoDoc.ResponseValue>{

    private DatosPerfilDataSource datosPerfilDataSource;

    public ObtenerTipoDoc(DatosPerfilDataSource datosPerfilDataSource) {
        this.datosPerfilDataSource = datosPerfilDataSource;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        datosPerfilDataSource.onObtenerNombreTipoDoc(requestValues.getKeyTipoDoc(), new DatosPerfilDataSource.CallBackResultado<String>() {
            @Override
            public void onResultado(String resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String keyTipoDoc;

        public RequestValues(String keyTipoDoc) {
            this.keyTipoDoc = keyTipoDoc;
        }

        public String getKeyTipoDoc() {
            return keyTipoDoc;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private String tipoDocNombre;

        public ResponseValue(String tipoDocNombre) {
            this.tipoDocNombre = tipoDocNombre;
        }

        public String getTipoDocNombre() {
            return tipoDocNombre;
        }
    }
}
