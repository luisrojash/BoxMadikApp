package com.application.boxmadikv1.cliente.menu.detallesCotizacion.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource.DetallesCotizacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.dataSource.DetallesCotizacionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public class EliminarPropuestaUnica extends UseCase<EliminarPropuestaUnica.RequestValues, EliminarPropuestaUnica.ResponseValue> {

    private DetallesCotizacionRepository detallesCotizacionRepository;

    public EliminarPropuestaUnica(DetallesCotizacionRepository detallesCotizacionRepository) {
        this.detallesCotizacionRepository = detallesCotizacionRepository;
    }



    @Override
    protected void executeUseCase(RequestValues requestValues) {
        detallesCotizacionRepository.onEliminarPropuestaUnica(requestValues.getDetallesCotizacionU()
                , new DetallesCotizacionDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onCallBackResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new EliminarPropuestaUnica.ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        DetallesCotizacionUi detallesCotizacionU;


        public RequestValues(DetallesCotizacionUi detallesCotizacionU) {
            this.detallesCotizacionU = detallesCotizacionU;

        }

        public DetallesCotizacionUi getDetallesCotizacionU() {
            return detallesCotizacionU;
        }

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private DefaultResponse defaultResponse;

        public ResponseValue(DefaultResponse defaultResponse) {
            this.defaultResponse = defaultResponse;
        }

        public DefaultResponse getDefaultResponse() {
            return defaultResponse;
        }
    }
}
