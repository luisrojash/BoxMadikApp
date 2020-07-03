package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.entidadUi.TipoDepartamentoUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource.UbicacionDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dialog.dataSource.UbicacionRepository;

import java.util.List;

public class ListaDepartamento extends UseCase<ListaDepartamento.RequestValues, ListaDepartamento.ResponseValue> {

    private UbicacionRepository ubicacionRepository;

    public ListaDepartamento(UbicacionRepository ubicacionRepository) {
        this.ubicacionRepository = ubicacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        ubicacionRepository.onListarDepartamento(requestValues.getCodigoPais(), new UbicacionDataSource.CallBackResultado<List<TipoDepartamentoUi>>() {
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
