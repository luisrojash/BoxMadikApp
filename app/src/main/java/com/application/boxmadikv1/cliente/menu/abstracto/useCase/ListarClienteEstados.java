package com.application.boxmadikv1.cliente.menu.abstracto.useCase;

import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.abstracto.dataSource.ClientAbstractDataSource;
import com.application.boxmadikv1.cliente.menu.abstracto.dataSource.ClientAbstractRepository;

public class ListarClienteEstados extends UseCase<ListarClienteEstados.RequestValues, ListarClienteEstados.ResponseValue> {

    private ClientAbstractRepository clientAbstractRepository;

    public ListarClienteEstados(ClientAbstractRepository clientAbstractRepository) {
        this.clientAbstractRepository = clientAbstractRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        clientAbstractRepository.mostrarListaClienteEstados(requestValues.getKeyUser(), requestValues
                        .getCodigoPais(), requestValues.getTipoEstado(),
                new ClientAbstractDataSource.CallBackResultado<ListaPropuestaPendienteResponse>() {
                    @Override
                    public void onCallBackResultado(ListaPropuestaPendienteResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String keyUser;
        private String tipoEstado;
        private String codigoPais;

        public RequestValues(String keyUser, String tipoEstado, String codigoPais) {
            this.keyUser = keyUser;
            this.tipoEstado = tipoEstado;
            this.codigoPais = codigoPais;
        }

        public String getKeyUser() {
            return keyUser;
        }

        public String getTipoEstado() {
            return tipoEstado;
        }

        public String getCodigoPais() {
            return codigoPais;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        ListaPropuestaPendienteResponse pendienteResponse;

        public ResponseValue(ListaPropuestaPendienteResponse pendienteResponse) {
            this.pendienteResponse = pendienteResponse;
        }

        public ListaPropuestaPendienteResponse getPendienteResponse() {
            return pendienteResponse;
        }
    }
}
