package com.application.boxmadikv1.terminosCondiciones.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.terminosCondiciones.dataSource.TerminosCondicionesDataSource;
import com.application.boxmadikv1.terminosCondiciones.dataSource.TerminosCondicionesRepository;

public class ObtenerTerminos extends UseCase<ObtenerTerminos.RequestValues, ObtenerTerminos.ResponseValue> {

    private TerminosCondicionesRepository terminosCondicionesRepository;

    public ObtenerTerminos(TerminosCondicionesRepository terminosCondicionesRepository) {
        this.terminosCondicionesRepository = terminosCondicionesRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        terminosCondicionesRepository.onObtenerTerminosCondiciones(requestValues.getTipoEstadoTerminos(), new TerminosCondicionesDataSource.CallBackResultado<String, String>() {
            @Override
            public void onCallBackResultado(String resultado, String resultado2) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado, resultado2));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private int tipoEstadoTerminos;

        public RequestValues(int tipoEstadoTerminos) {
            this.tipoEstadoTerminos = tipoEstadoTerminos;
        }

        public int getTipoEstadoTerminos() {
            return tipoEstadoTerminos;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private String textoTerminosCondiciones;
        private String tipoEstado;

        public ResponseValue(String textoTerminosCondiciones, String tipoEstado) {
            this.textoTerminosCondiciones = textoTerminosCondiciones;
            this.tipoEstado = tipoEstado;
        }

        public String getTextoTerminosCondiciones() {
            return textoTerminosCondiciones;
        }
    }
}
