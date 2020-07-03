package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.RevocacionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.dataSource.RevocacionRepository;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion.entidad.TipoSolucionRevocacionUi;

import java.util.List;

public class ObtenerListaSolucionRevocacion extends UseCase<ObtenerListaSolucionRevocacion.RequestValues,ObtenerListaSolucionRevocacion.ResponseValue>{

    private RevocacionRepository revocacionRepository;

    public ObtenerListaSolucionRevocacion(RevocacionRepository revocacionRepository) {
        this.revocacionRepository = revocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        revocacionRepository.onListarTipoSolucionRevocacion(new RevocacionDataSource.CallBackResultado<List<TipoSolucionRevocacionUi>>() {
            @Override
            public void onCallBackResultado(List<TipoSolucionRevocacionUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{

    }
    public static final class ResponseValue implements UseCase.ResponseValue{
        List<TipoSolucionRevocacionUi> tipoSolucionRevocacionUis;

        public ResponseValue(List<TipoSolucionRevocacionUi> tipoSolucionRevocacionUis) {
            this.tipoSolucionRevocacionUis = tipoSolucionRevocacionUis;
        }

        public List<TipoSolucionRevocacionUi> getTipoSolucionRevocacionUis() {
            return tipoSolucionRevocacionUis;
        }

        public void setTipoSolucionRevocacionUis(List<TipoSolucionRevocacionUi> tipoSolucionRevocacionUis) {
            this.tipoSolucionRevocacionUis = tipoSolucionRevocacionUis;
        }
    }
}
