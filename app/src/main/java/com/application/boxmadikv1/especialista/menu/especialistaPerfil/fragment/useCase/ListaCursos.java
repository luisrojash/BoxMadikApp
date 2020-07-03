package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.useCase;

import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.dataSource.ComentarioDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.dataSource.ComentarioRepository;

public class ListaCursos extends UseCase<ListaCursos.RequestValues, ListaCursos.ResponseValue> {

    private ComentarioRepository comentarioRepository;

    public ListaCursos(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        comentarioRepository.onMostrarListaCursos(requestValues.getKeyUser(), new ComentarioDataSource.CallBackResultado<ListaCursosResponse>() {
            @Override
            public void onCallBackResultado(ListaCursosResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String keyUser;

        public RequestValues(String keyUser) {
            this.keyUser = keyUser;
        }

        public String getKeyUser() {
            return keyUser;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private ListaCursosResponse listaCursosResponse;

        public ResponseValue(ListaCursosResponse listaCursosResponse) {
            this.listaCursosResponse = listaCursosResponse;
        }

        public ListaCursosResponse getListaCursosResponse() {
            return listaCursosResponse;
        }
    }
}
