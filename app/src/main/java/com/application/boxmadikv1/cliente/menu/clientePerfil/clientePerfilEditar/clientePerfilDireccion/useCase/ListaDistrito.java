package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionDataSource;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDistritoUi;

import java.util.List;

public class ListaDistrito extends UseCase<ListaDistrito.RequestValues, ListaDistrito.ResponseValue> {

    private ClientePerfilDireccionRepository clientePerfilDireccionRepository;


    public ListaDistrito(ClientePerfilDireccionRepository clientePerfilDireccionRepository) {
        this.clientePerfilDireccionRepository = clientePerfilDireccionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        clientePerfilDireccionRepository.onListarDistrito(requestValues.getPaisCodigo(), requestValues.getDepartamentoCodigo(), requestValues.getProvinciaCodigo(),
                new ClientePerfilDireccionDataSource.CallBackResultadoDistrito() {
                    @Override
                    public void onResultado(List<TipoDistritoUi> tipoDistritoUis) {
                        getUseCaseCallback().onSuccess(new ResponseValue(tipoDistritoUis));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String paisCodigo;
        private String departamentoCodigo;
        private String provinciaCodigo;

        public RequestValues(String paisCodigo, String departamentoCodigo, String provinciaCodigo) {
            this.paisCodigo = paisCodigo;
            this.departamentoCodigo = departamentoCodigo;
            this.provinciaCodigo = provinciaCodigo;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }

        public String getDepartamentoCodigo() {
            return departamentoCodigo;
        }

        public String getProvinciaCodigo() {
            return provinciaCodigo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoDistritoUi> tipoDistritoUis;

        public ResponseValue(List<TipoDistritoUi> tipoDistritoUis) {
            this.tipoDistritoUis = tipoDistritoUis;
        }

        public List<TipoDistritoUi> getTipoDistritoUis() {
            return tipoDistritoUis;
        }
    }
}
