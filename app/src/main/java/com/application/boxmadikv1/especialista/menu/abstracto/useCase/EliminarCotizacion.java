package com.application.boxmadikv1.especialista.menu.abstracto.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.EspecAbstractDataSource;
import com.application.boxmadikv1.especialista.menu.abstracto.dataSource.EspecAbstractRepository;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;


public class EliminarCotizacion extends UseCase<EliminarCotizacion.RequestValues, EliminarCotizacion.ResponseValue> {

    private EspecAbstractRepository especAbstractRepository;

    public EliminarCotizacion(EspecAbstractRepository especAbstractRepository) {
        this.especAbstractRepository = especAbstractRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        especAbstractRepository.eliminarItem(requestValues.getEspecialistaEstadosUi(), requestValues.getKeyUser(),
                new EspecAbstractDataSource.CallBackResultado<DefaultResponse>() {
                    @Override
                    public void onCallBackResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });


    }

    public static final class RequestValues implements UseCase.RequestValues {
        private EspecialistaEstadosUi especialistaEstadosUi;
        private String keyUser;


        public RequestValues(EspecialistaEstadosUi especialistaEstadosUi, String keyUser) {
            this.especialistaEstadosUi = especialistaEstadosUi;
            this.keyUser = keyUser;
        }

        public EspecialistaEstadosUi getEspecialistaEstadosUi() {
            return especialistaEstadosUi;
        }

        public String getKeyUser() {
            return keyUser;
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
