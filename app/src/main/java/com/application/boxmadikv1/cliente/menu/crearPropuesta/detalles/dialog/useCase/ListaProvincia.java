package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource.UbicacionDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource.UbicacionRepository;

import java.util.List;

public class ListaProvincia extends UseCase<ListaProvincia.RequestValues, ListaProvincia.ResponseValue> {

    private UbicacionRepository ubicacionRepository;

    public ListaProvincia(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        ubicacionRepository.onListarProvincia(requestValues.getPaisCodigo(), requestValues.getDepartamentoCodigo(),
                new UbicacionDataSource.CallBackResultado<List<TipoProvinciaUi>>() {
                    @Override
                    public void onCallBackResultado(List<TipoProvinciaUi> resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String paisCodigo;
        private String departamentoCodigo;

        public RequestValues(String paisCodigo, String departamentoCodigo) {
            this.paisCodigo = paisCodigo;
            this.departamentoCodigo = departamentoCodigo;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }

        public String getDepartamentoCodigo() {
            return departamentoCodigo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoProvinciaUi> tipoProvinciaUis;

        public ResponseValue(List<TipoProvinciaUi> tipoProvinciaUis) {
            this.tipoProvinciaUis = tipoProvinciaUis;
        }

        public List<TipoProvinciaUi> getTipoProvinciaUis() {
            return tipoProvinciaUis;
        }
    }
}
