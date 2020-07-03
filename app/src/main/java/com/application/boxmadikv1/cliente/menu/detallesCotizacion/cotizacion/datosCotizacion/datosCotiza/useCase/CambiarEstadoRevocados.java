package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.dataSource.DatosCotizaRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public class CambiarEstadoRevocados extends UseCase<CambiarEstadoRevocados.RequestValues, CambiarEstadoRevocados.ResponseValue> {

    private RevocacionRepository revocacionRepository;

    public CambiarEstadoRevocados(RevocacionRepository revocacionRepository) {
        this.revocacionRepository = revocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        revocacionRepository.onCambiarEstadoPropuestaYCoti(requestValues.getDetallesCotizacionUi(), requestValues.getCotizacionesUi(),
                new RevocacionDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private DetallesCotizacionUi detallesCotizacionUi;
        private CotizacionesUi cotizacionesUi;

        public RequestValues(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
            this.detallesCotizacionUi = detallesCotizacionUi;
            this.cotizacionesUi = cotizacionesUi;
        }

        public DetallesCotizacionUi getDetallesCotizacionUi() {
            return detallesCotizacionUi;
        }

        public CotizacionesUi getCotizacionesUi() {
            return cotizacionesUi;
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
