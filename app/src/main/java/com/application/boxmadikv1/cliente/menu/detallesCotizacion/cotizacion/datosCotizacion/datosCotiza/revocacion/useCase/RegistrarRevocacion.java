package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public class RegistrarRevocacion extends UseCase<RegistrarRevocacion.RequestValues,RegistrarRevocacion.ResponseValue>{

    private RevocacionRepository revocacionRepository;

    public RegistrarRevocacion(RevocacionRepository revocacionRepository) {
        this.revocacionRepository = revocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        revocacionRepository.onRegistrarRevocacion(requestValues.getIdTipoMotivoRevocacion(),
                requestValues.getIdTipoCalidadTrabajo(),
                requestValues.getValorPorcentajeTrabajo(),
                requestValues.idSolicitaRevocante,
                requestValues.getObservacion(),
                requestValues.detallesCotizacionUi,
                requestValues.getCotizacionesUi(),
                new RevocacionDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String idTipoMotivoRevocacion;
        private String idTipoCalidadTrabajo;
        private String valorPorcentajeTrabajo;
        private String idSolicitaRevocante;
        private String observacion;
        private DetallesCotizacionUi detallesCotizacionUi;
        private CotizacionesUi cotizacionesUi;

        public RequestValues(String idTipoMotivoRevocacion, String idTipoCalidadTrabajo, String valorPorcentajeTrabajo, String idSolicitaRevocante, String observacion, DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi) {
            this.idTipoMotivoRevocacion = idTipoMotivoRevocacion;
            this.idTipoCalidadTrabajo = idTipoCalidadTrabajo;
            this.valorPorcentajeTrabajo = valorPorcentajeTrabajo;
            this.idSolicitaRevocante = idSolicitaRevocante;
            this.observacion = observacion;
            this.detallesCotizacionUi = detallesCotizacionUi;
            this.cotizacionesUi = cotizacionesUi;
        }

        public String getIdTipoMotivoRevocacion() {
            return idTipoMotivoRevocacion;
        }

        public String getIdTipoCalidadTrabajo() {
            return idTipoCalidadTrabajo;
        }

        public String getValorPorcentajeTrabajo() {
            return valorPorcentajeTrabajo;
        }

        public String getIdSolicitaRevocante() {
            return idSolicitaRevocante;
        }

        public String getObservacion() {
            return observacion;
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
