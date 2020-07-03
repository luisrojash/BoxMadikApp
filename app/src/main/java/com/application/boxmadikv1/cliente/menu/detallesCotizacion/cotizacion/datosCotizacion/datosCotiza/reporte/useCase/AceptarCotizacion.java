package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource.ReportePagoDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte.dataSource.ReportePagoRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public class AceptarCotizacion extends UseCase<AceptarCotizacion.RequestValues,AceptarCotizacion.ResponseValue>{

    private ReportePagoRepository reportePagoRepository;

    public AceptarCotizacion(ReportePagoRepository reportePagoRepository) {
        this.reportePagoRepository = reportePagoRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        reportePagoRepository.onAceptarCotizacion(requestValues.getDetallesCotizacionUi(), requestValues.getCotizacionesUi(),
                new ReportePagoDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onCallBackResultado(DefaultResponse resultado) {
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
        DefaultResponse defaultResponse;

        public ResponseValue(DefaultResponse defaultResponse) {
            this.defaultResponse = defaultResponse;
        }

        public DefaultResponse getDefaultResponse() {
            return defaultResponse;
        }
    }
}
