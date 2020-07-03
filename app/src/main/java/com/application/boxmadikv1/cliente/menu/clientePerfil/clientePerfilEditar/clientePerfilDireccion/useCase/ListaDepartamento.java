package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionDataSource;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;

import java.util.List;

public class ListaDepartamento extends UseCase<ListaDepartamento.RequestValues, ListaDepartamento.ResponseValue> {


    private ClientePerfilDireccionRepository clientePerfilDireccionRepository;

    public ListaDepartamento(ClientePerfilDireccionRepository clientePerfilDireccionRepository) {
        this.clientePerfilDireccionRepository = clientePerfilDireccionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        clientePerfilDireccionRepository.onListarDepartamento(new ClientePerfilDireccionDataSource.
                CallBackResultado() {
            @Override
            public void onResultado(List<TipoDepartamentoUi> tipoDepartamentoUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(tipoDepartamentoUis));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoDepartamentoUi> tipoDepartamentoUis;

        public ResponseValue(List<TipoDepartamentoUi> tipoDepartamentoUis) {
            this.tipoDepartamentoUis = tipoDepartamentoUis;
        }

        public List<TipoDepartamentoUi> getTipoDepartamentoUis() {
            return tipoDepartamentoUis;
        }
    }
}
