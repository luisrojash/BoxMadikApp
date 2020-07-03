package com.application.boxmadikv1.especialista.menu.especialistaPerfil.useCase;

import com.application.boxmadikv1.api.response.especialista.ComentarioResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource.EspecialistaPerfilDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource.EspecialistaPerfilRepository;

public class MostrarComentarios extends UseCase<MostrarComentarios.RequestValues, MostrarComentarios.ResponseValue> {

    private EspecialistaPerfilRepository especialistaPerfilRepository;

    public MostrarComentarios(EspecialistaPerfilRepository especialistaPerfilRepository) {
        this.especialistaPerfilRepository = especialistaPerfilRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        especialistaPerfilRepository.onMostrarComentario(requestValues.getUserKey(), requestValues.getPaisCodigo(), new EspecialistaPerfilDataSource.CallBackResultado<ComentarioResponse>() {
            @Override
            public void onCallbackResultado(ComentarioResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String userKey;
        private String paisCodigo;

        public RequestValues(String userKey, String paisCodigo) {
            this.userKey = userKey;
            this.paisCodigo = paisCodigo;
        }

        public String getUserKey() {
            return userKey;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private ComentarioResponse comentarioResponse;

        public ResponseValue(ComentarioResponse comentarioResponse) {
            this.comentarioResponse = comentarioResponse;
        }

        public ComentarioResponse getComentarioResponse() {
            return comentarioResponse;
        }
    }
}
