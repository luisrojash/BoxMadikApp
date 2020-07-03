package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.RevocacionRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoMotivoRevocacionUi;

import java.util.List;

public class ObtenerListaMotivoRevocacion extends UseCase<ObtenerListaMotivoRevocacion.RequestValues, ObtenerListaMotivoRevocacion.ResponseValue> {

    RevocacionRepository revocacionRepository;

    public ObtenerListaMotivoRevocacion(RevocacionRepository revocacionRepository) {
        this.revocacionRepository = revocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        revocacionRepository.onListarTipoMotivoRevocacion(new RevocacionDataSource.CallBackResultado<List<TipoMotivoRevocacionUi>>() {
            @Override
            public void onCallBackResultado(List<TipoMotivoRevocacionUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUiList;

        public ResponseValue(List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUiList) {
            this.tipoMotivoRevocacionUiList = tipoMotivoRevocacionUiList;
        }

        public List<TipoMotivoRevocacionUi> getTipoMotivoRevocacionUiList() {
            return tipoMotivoRevocacionUiList;
        }

        public void setTipoMotivoRevocacionUiList(List<TipoMotivoRevocacionUi> tipoMotivoRevocacionUiList) {
            this.tipoMotivoRevocacionUiList = tipoMotivoRevocacionUiList;
        }

    }
}

