package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionDataSource;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoProvinciaUi;

import java.util.List;

public class ListaProvincia extends UseCase<ListaProvincia.RequestValues, ListaProvincia.ResponseValue> {

    private ClientePerfilDireccionRepository clientePerfilDireccionRepository;

    public ListaProvincia(ClientePerfilDireccionRepository clientePerfilDireccionRepository) {
        this.clientePerfilDireccionRepository = clientePerfilDireccionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        clientePerfilDireccionRepository.onListarProvincia(requestValues.getPaisCodigo(), requestValues.getDepartamentoCodigo(), new ClientePerfilDireccionDataSource.CallBackResultadoProvincia() {
            @Override
            public void onResultado(List<TipoProvinciaUi> tipoProvinciaUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(tipoProvinciaUis));
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
