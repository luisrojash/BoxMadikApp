package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.AtencionDataSource;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.dataSource.AtencionRepository;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoPrecioUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoTurnoUi;

import java.util.List;

public class ListarTipoTurno extends UseCase<ListarTipoTurno.RequestValues,ListarTipoTurno.ResponseValue>{

    AtencionRepository atencionRepository;

    public ListarTipoTurno(AtencionRepository atencionRepository) {
        this.atencionRepository = atencionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        atencionRepository.onListartTipoTurno(new AtencionDataSource.CallbackResultado<List<TipoRangoTurnoUi>>() {
            @Override
            public void onCallBackResultado(List<TipoRangoTurnoUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{

    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        List<TipoRangoTurnoUi> tipoRangoTurnoUiList;

        public ResponseValue(List<TipoRangoTurnoUi> tipoRangoTurnoUiList) {
            this.tipoRangoTurnoUiList = tipoRangoTurnoUiList;
        }

        public List<TipoRangoTurnoUi> getTipoRangoTurnoUiList() {
            return tipoRangoTurnoUiList;
        }
    }
}
