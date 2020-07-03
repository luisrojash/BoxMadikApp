package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;

import java.util.List;

public class MostrarListaTipoCalidadTrabajoUi extends UseCase<MostrarListaTipoCalidadTrabajoUi.RequestValues,MostrarListaTipoCalidadTrabajoUi.ResponseValue> {

    private RevocacionRepository revocacionRepository;

    public MostrarListaTipoCalidadTrabajoUi(RevocacionRepository revocacionRepository) {
        this.revocacionRepository = revocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        revocacionRepository.onMostrarListaTipoCalidad(new RevocacionDataSource.CallBackResultado<List<TipoCalidadTrabajoUi>>() {
            @Override
            public void onResultado(List<TipoCalidadTrabajoUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoCalidadTrabajoUi> tipoCalidadTrabajoUiList;

        public ResponseValue(List<TipoCalidadTrabajoUi> tipoCalidadTrabajoUiList) {
            this.tipoCalidadTrabajoUiList = tipoCalidadTrabajoUiList;
        }

        public List<TipoCalidadTrabajoUi> getTipoCalidadTrabajoUiList() {
            return tipoCalidadTrabajoUiList;
        }
    }
}
