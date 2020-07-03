package com.application.boxmadikv1.cliente.menu.detallesCotizacion.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource.DetallesCotizacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource.DetallesCotizacionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public class EliminarPropuesta extends UseCase<EliminarPropuesta.RequestValues,EliminarPropuesta.ResponseValue> {

    private DetallesCotizacionRepository detallesCotizacionRepository;

    public EliminarPropuesta(DetallesCotizacionRepository detallesCotizacionRepository) {
        this.detallesCotizacionRepository = detallesCotizacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        detallesCotizacionRepository.onEliminarPropuesta(requestValues.getDetallesCotizacionU(),requestValues.getCotizacionesUis(), new DetallesCotizacionDataSource.CallBackResultado<DefaultResponse>() {
            @Override
            public void onCallBackResultado(DefaultResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{
        DetallesCotizacionUi detallesCotizacionU;
        List<CotizacionesUi> cotizacionesUis;

        public RequestValues(DetallesCotizacionUi detallesCotizacionU, List<CotizacionesUi> cotizacionesUis) {
            this.detallesCotizacionU = detallesCotizacionU;
            this.cotizacionesUis = cotizacionesUis;
        }

        public DetallesCotizacionUi getDetallesCotizacionU() {
            return detallesCotizacionU;
        }

        public List<CotizacionesUi> getCotizacionesUis() {
            return cotizacionesUis;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        private DefaultResponse defaultResponse;

        public ResponseValue(DefaultResponse defaultResponse) {
            this.defaultResponse = defaultResponse;
        }

        public DefaultResponse getDefaultResponse() {
            return defaultResponse;
        }
    }
}
