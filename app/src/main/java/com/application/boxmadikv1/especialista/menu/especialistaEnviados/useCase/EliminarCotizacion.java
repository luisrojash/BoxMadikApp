package com.application.boxmadikv1.especialista.menu.especialistaEnviados.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosRepository;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;

public class EliminarCotizacion extends UseCase<EliminarCotizacion.RequestValues,EliminarCotizacion.ResponseValue>{

    private EspecialistaEnviadosRepository especialistaEnviadosRepository;

    public EliminarCotizacion(EspecialistaEnviadosRepository especialistaEnviadosRepository) {
        this.especialistaEnviadosRepository = especialistaEnviadosRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        especialistaEnviadosRepository.eliminarItem(requestValues.getEspecialistaEnviadosUi(), requestValues.getKeyUser(), new EspecialistaEnviadosDataSource.CallBackResultado<DefaultResponse>() {
            @Override
            public void onCallBackResultado(DefaultResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });


    }

    public static final class RequestValues implements UseCase.RequestValues {
       private EspecialistaEnviadosUi especialistaEnviadosUi;
       private String keyUser;


        public RequestValues(EspecialistaEnviadosUi especialistaEnviadosUi, String keyUser) {
            this.especialistaEnviadosUi = especialistaEnviadosUi;
            this.keyUser = keyUser;
        }

        public EspecialistaEnviadosUi getEspecialistaEnviadosUi() {
            return especialistaEnviadosUi;
        }

        public String getKeyUser() {
            return keyUser;
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
