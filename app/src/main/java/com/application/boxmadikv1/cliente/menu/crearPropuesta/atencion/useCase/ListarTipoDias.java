package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.AtencionDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.AtencionRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoDiasUi;
import java.util.List;

public class ListarTipoDias extends UseCase<ListarTipoDias.RequestValues, ListarTipoDias.ResponseValue> {

    AtencionRepository atencionRepository;

    public ListarTipoDias(AtencionRepository atencionRepository) {
        this.atencionRepository = atencionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        atencionRepository.onListartTipoDias(new AtencionDataSource.CallbackResultado<List<TipoRangoDiasUi>>() {
            @Override
            public void onCallBackResultado(List<TipoRangoDiasUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<TipoRangoDiasUi> tipoRangoDiasUis;

        public ResponseValue(List<TipoRangoDiasUi> tipoRangoDiasUis) {
            this.tipoRangoDiasUis = tipoRangoDiasUis;
        }

        public List<TipoRangoDiasUi> getTipoRangoDiasUis() {
            return tipoRangoDiasUis;
        }
    }
}
