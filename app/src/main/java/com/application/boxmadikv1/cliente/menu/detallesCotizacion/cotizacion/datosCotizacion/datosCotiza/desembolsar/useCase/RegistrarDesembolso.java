package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource.DesembolsarDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar.dataSource.DesembolsarRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public class RegistrarDesembolso extends UseCase<RegistrarDesembolso.RequesValues,RegistrarDesembolso.ResponseValue> {

    private DesembolsarRepository desembolsarRepository;

    public RegistrarDesembolso(DesembolsarRepository desembolsarRepository) {
        this.desembolsarRepository = desembolsarRepository;
    }

    @Override
    protected void executeUseCase(RequesValues requestValues) {
        desembolsarRepository.onRegistrarDesembolso(requestValues.getRatingValue(),
                requestValues.getEditTextComentario(),
                requestValues.getDetallesCotizacionUi(),
                requestValues.getCotizacionesUi(),
                new DesembolsarDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onCallBackResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequesValues implements UseCase.RequestValues{
        private String ratingValue;
        private String editTextComentario;
        private DetallesCotizacionUi detallesCotizacionUi;
        private CotizacionesUi cotizacionesUi;

        public RequesValues(String ratingValue, String editTextComentario, DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
            this.ratingValue = ratingValue;
            this.editTextComentario = editTextComentario;
            this.detallesCotizacionUi = detallesCotizacionUi;
            this.cotizacionesUi = cotizacionesUi;
        }

        public String getRatingValue() {
            return ratingValue;
        }

        public String getEditTextComentario() {
            return editTextComentario;
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
