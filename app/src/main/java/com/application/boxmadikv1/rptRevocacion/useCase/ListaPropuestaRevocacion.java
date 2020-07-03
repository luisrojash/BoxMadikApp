package com.application.boxmadikv1.rptRevocacion.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.rptRevocacion.dataSource.RespuestaRevocacionDataSource;
import com.application.boxmadikv1.rptRevocacion.dataSource.RespuestaRevocacionRepository;
import com.application.boxmadikv1.rptRevocacion.entidad.PropuestaRevocacionUi;

import java.util.List;

public class ListaPropuestaRevocacion extends UseCase<ListaPropuestaRevocacion.RequestValues, ListaPropuestaRevocacion.ResponseValue> {

    private RespuestaRevocacionRepository respuestaRevocacionRepository;

    public ListaPropuestaRevocacion(RespuestaRevocacionRepository respuestaRevocacionRepository) {
        this.respuestaRevocacionRepository = respuestaRevocacionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        respuestaRevocacionRepository.onMostrarListaRevocacionPropuesta(new RespuestaRevocacionDataSource.onCallBackResultado<List<PropuestaRevocacionUi>>() {
            @Override
            public void onResultado(List<PropuestaRevocacionUi> resltado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resltado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<PropuestaRevocacionUi> propuestaRevocacionUis;

        public ResponseValue(List<PropuestaRevocacionUi> documentoUiList) {
            this.propuestaRevocacionUis = documentoUiList;
        }

        public List<PropuestaRevocacionUi> getDocumentoUiList() {
            return propuestaRevocacionUis;
        }

        public void setDocumentoUiList(List<PropuestaRevocacionUi> documentoUiList) {
            this.propuestaRevocacionUis = documentoUiList;
        }
    }
}
