package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoCalidadTrabajoUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoMotivoRevocacionUi;

import java.util.List;

public class MostrarListaTipoMotivoRevocacionUi extends UseCase<MostrarListaTipoMotivoRevocacionUi.RequestValues, MostrarListaTipoMotivoRevocacionUi.ResponseValue> {
    private RevocacionRepository revocacionRepository;

    public MostrarListaTipoMotivoRevocacionUi(RevocacionRepository revocacionRepository) {
        this.revocacionRepository = revocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        revocacionRepository.onMostrarListaTipoMotivoRevocacion(new RevocacionDataSource.CallBackResultado<List<TipoMotivoRevocacionUi>>() {
            @Override
            public void onResultado(List<TipoMotivoRevocacionUi> resultado) {
                    getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUiList;

        public ResponseValue(List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUiList) {
            this.tipoMotivoRevocacionUiList = tipoMotivoRevocacionUiList;
        }

        public List<TipoMotivoRevocacionUi> getTipoMotivoRevocacionUiList() {
            return tipoMotivoRevocacionUiList;
        }
    }
}
