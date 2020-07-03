package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.AtencionDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.AtencionRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoPrecioUi;

import java.util.List;

public class ListarTipoPrecio extends UseCase<ListarTipoPrecio.RequestValues,ListarTipoPrecio.ResponseValue>{

    AtencionRepository atencionRepository;

    public ListarTipoPrecio(AtencionRepository atencionRepository) {
        this.atencionRepository = atencionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        atencionRepository.onListartTipoPrecio(requestValues.getCodigoPais(),new AtencionDataSource.CallbackResultado<List<TipoRangoPrecioUi>>() {
            @Override
            public void onCallBackResultado(List<TipoRangoPrecioUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        private String codigoPais;

        public RequestValues(String codigoPais) {
            this.codigoPais = codigoPais;
        }

        public String getCodigoPais() {
            return codigoPais;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        List<TipoRangoPrecioUi> tipoRangoPrecioUiList;

        public ResponseValue(List<TipoRangoPrecioUi> tipoRangoPrecioUiList) {
            this.tipoRangoPrecioUiList = tipoRangoPrecioUiList;
        }

        public List<TipoRangoPrecioUi> getTipoRangoPrecioUiList() {
            return tipoRangoPrecioUiList;
        }
    }
}
