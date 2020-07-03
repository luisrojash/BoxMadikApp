package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.RevocacionRepository;

public class RegistrarRevocacionEspecialista extends UseCase<RegistrarRevocacionEspecialista.RequestValues, RegistrarRevocacionEspecialista.ResponseValue> {

    private RevocacionRepository revocacionRepository;

    public RegistrarRevocacionEspecialista(RevocacionRepository revocacionRepository) {
        this.revocacionRepository = revocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        revocacionRepository.onRegistrarRevocacion(requestValues.getItemUi(), requestValues.getIdTipSolucionRevocacion(), requestValues.getIdTipoMotivoRevocacion(), requestValues.getValorPorcentajeTrabajo(), requestValues.getObservacion(),
                new RevocacionDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onCallBackResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private ItemUi itemUi;
        String idTipSolucionRevocacion;
        String idTipoMotivoRevocacion;
        int valorPorcentajeTrabajo;
        String observacion;

        public RequestValues(ItemUi itemUi, String idTipSolucionRevocacion, String idTipoMotivoRevocacion, int valorPorcentajeTrabajo, String observacion) {
            this.itemUi = itemUi;
            this.idTipSolucionRevocacion = idTipSolucionRevocacion;
            this.idTipoMotivoRevocacion = idTipoMotivoRevocacion;
            this.valorPorcentajeTrabajo = valorPorcentajeTrabajo;
            this.observacion = observacion;
        }

        public ItemUi getItemUi() {
            return itemUi;
        }

        public String getIdTipSolucionRevocacion() {
            return idTipSolucionRevocacion;
        }

        public String getIdTipoMotivoRevocacion() {
            return idTipoMotivoRevocacion;
        }

        public int getValorPorcentajeTrabajo() {
            return valorPorcentajeTrabajo;
        }

        public String getObservacion() {
            return observacion;
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
