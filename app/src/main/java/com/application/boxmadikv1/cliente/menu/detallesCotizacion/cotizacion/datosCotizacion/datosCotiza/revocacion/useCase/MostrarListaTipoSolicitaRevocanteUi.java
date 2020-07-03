package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.dataSource.RevocacionRepository;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.revocacion.entidad.TipoSolicitaRevocanteUi;

import java.util.List;

public class MostrarListaTipoSolicitaRevocanteUi extends UseCase<MostrarListaTipoSolicitaRevocanteUi.RequestValues, MostrarListaTipoSolicitaRevocanteUi.ResponseValue> {

    private RevocacionRepository revocacionRepository;

    public MostrarListaTipoSolicitaRevocanteUi(RevocacionRepository revocacionRepository) {
        this.revocacionRepository = revocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        revocacionRepository.onMostrarListaTipoSolicitaRevocante(new RevocacionDataSource.CallBackResultado<List<TipoSolicitaRevocanteUi>>() {
            @Override
            public void onResultado(List<TipoSolicitaRevocanteUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoSolicitaRevocanteUi> solicitaRevocanteUiList;

        public ResponseValue(List<TipoSolicitaRevocanteUi> solicitaRevocanteUiList) {
            this.solicitaRevocanteUiList = solicitaRevocanteUiList;
        }

        public List<TipoSolicitaRevocanteUi> getSolicitaRevocanteUiList() {
            return solicitaRevocanteUiList;
        }
    }
}
