package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.useCase;

import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.dataSource.DescripcionDataSource;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.dataSource.DescripcionRepository;

public class MostrarImagenPropuesta extends UseCase<MostrarImagenPropuesta.RequestValues, MostrarImagenPropuesta.ResponseValue> {

    private DescripcionRepository descripcionRepository;

    public MostrarImagenPropuesta(DescripcionRepository descripcionRepository) {
        this.descripcionRepository = descripcionRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        descripcionRepository.onMostrarImagenPropuesta(requestValues.getCodigo_propuesta_inicial(), new DescripcionDataSource.CallbackResultado<MostrarImagenResponse>() {
            @Override
            public void onResultado(MostrarImagenResponse primerResultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(primerResultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        String codigo_propuesta_inicial;

        public RequestValues(String codigo_propuesta_inicial) {
            this.codigo_propuesta_inicial = codigo_propuesta_inicial;
        }

        public String getCodigo_propuesta_inicial() {
            return codigo_propuesta_inicial;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        MostrarImagenResponse mostrarImagenResponse;

        public ResponseValue(MostrarImagenResponse mostrarImagenResponse) {
            this.mostrarImagenResponse = mostrarImagenResponse;
        }

        public MostrarImagenResponse getMostrarImagenResponse() {
            return mostrarImagenResponse;
        }
    }

}
