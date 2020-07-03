package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionDataSource;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.dataSource.ClientePerfilDireccionRepository;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource.EspecialistaPerfilDistritoDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.dataSource.EspecialistaPerfilDistritoRepository;

import java.util.List;

public class ListaDepartamento extends UseCase<ListaDepartamento.RequestValues, ListaDepartamento.ResponseValue> {


    private EspecialistaPerfilDistritoRepository clientePerfilDireccionRepository;

    public ListaDepartamento(EspecialistaPerfilDistritoRepository clientePerfilDireccionRepository) {
        this.clientePerfilDireccionRepository = clientePerfilDireccionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        clientePerfilDireccionRepository.onListarDepartamento(requestValues.getCodigoPais(), new EspecialistaPerfilDistritoDataSource.CallBackResultado<List<TipoDepartamentoUi>>() {
            @Override
            public void onCallBackResultado(List<TipoDepartamentoUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String codigoPais;

        public RequestValues(String codigoPais) {
            this.codigoPais = codigoPais;
        }

        public String getCodigoPais() {
            return codigoPais;
        }
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
