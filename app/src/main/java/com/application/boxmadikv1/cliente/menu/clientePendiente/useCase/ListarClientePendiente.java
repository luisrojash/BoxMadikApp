package com.application.boxmadikv1.cliente.menu.clientePendiente.useCase;

import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource.ClientePendienteDataSource;
import com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource.ClientePendienteRepository;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;

import java.util.List;

public class ListarClientePendiente extends UseCase<ListarClientePendiente.RequestValues,ListarClientePendiente.ResponseValue>{

    private ClientePendienteRepository clienteTodosRepository;

    public ListarClientePendiente(ClientePendienteRepository clienteTodosRepository) {
        this.clienteTodosRepository = clienteTodosRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        clienteTodosRepository.mostrarListaClienteTodos(requestValues.getKeyUser(),new ClientePendienteDataSource.CallBackResultado<ListaPropuestaPendienteResponse>() {
            @Override
            public void onCallBackResultado(ListaPropuestaPendienteResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String keyUser ;

        public RequestValues(String keyUser) {
            this.keyUser = keyUser;
        }

        public String getKeyUser() {
            return keyUser;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        ListaPropuestaPendienteResponse pendienteResponse;

        public ResponseValue(ListaPropuestaPendienteResponse pendienteResponse) {
            this.pendienteResponse = pendienteResponse;
        }

        public ListaPropuestaPendienteResponse getPendienteResponse() {
            return pendienteResponse;
        }
    }
}
