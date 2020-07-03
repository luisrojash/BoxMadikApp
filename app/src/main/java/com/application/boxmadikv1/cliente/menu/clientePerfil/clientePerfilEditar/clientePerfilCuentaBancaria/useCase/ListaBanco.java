package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.dataSource.ClientePerfilCuentaBancariaDataSource;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.dataSource.ClientePerfilCuentaBancariaRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilCuentaBancaria.entidadUi.TipoBancoUi;

import java.util.List;

public class ListaBanco extends UseCase<ListaBanco.RequestValues,ListaBanco.ResponseValue>{

    private ClientePerfilCuentaBancariaRepository clientePerfilCuentaBancariaRepository;

    public ListaBanco(ClientePerfilCuentaBancariaRepository clientePerfilCuentaBancariaRepository) {
        this.clientePerfilCuentaBancariaRepository = clientePerfilCuentaBancariaRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        clientePerfilCuentaBancariaRepository.onListarBanco(new ClientePerfilCuentaBancariaDataSource.CallBackResultado() {
            @Override
            public void onResultado(List<TipoBancoUi> tipoBancoUis) {
                getUseCaseCallback().onSuccess(new ResponseValue(tipoBancoUis));
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements  UseCase.ResponseValue{
        private List<TipoBancoUi> tipoBancoUis;

        public ResponseValue(List<TipoBancoUi> tipoBancoUis) {
            this.tipoBancoUis = tipoBancoUis;
        }

        public List<TipoBancoUi> getTipoBancoUis() {
            return tipoBancoUis;
        }
    }
}
