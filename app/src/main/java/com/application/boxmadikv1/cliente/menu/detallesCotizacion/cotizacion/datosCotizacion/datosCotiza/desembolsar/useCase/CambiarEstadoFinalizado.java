package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource.DesembolsarDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource.DesembolsarRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public class CambiarEstadoFinalizado extends UseCase<CambiarEstadoFinalizado.RequesValues,CambiarEstadoFinalizado.ResponseValue> {

    private DesembolsarRepository desembolsarRepository;

    public CambiarEstadoFinalizado(DesembolsarRepository desembolsarRepository) {
        this.desembolsarRepository = desembolsarRepository;
    }

    @Override
    protected void executeUseCase(RequesValues requestValues) {
        desembolsarRepository.onCambiarEstadoFinalizado(requestValues.getDetallesCotizacionUi(), requestValues.getCotizacionesUi(),
                new DesembolsarDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onCallBackResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequesValues implements UseCase.RequestValues{
        private DetallesCotizacionUi detallesCotizacionUi;
        private CotizacionesUi cotizacionesUi;

        public RequesValues(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
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
