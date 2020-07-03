package com.application.boxmadikv1.especialista.menu.especialistaEnviados.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.EspecialistaEnviadosRepository;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;

import java.util.List;

public class ListarEspecialistaEnviados extends UseCase<ListarEspecialistaEnviados.RequestValues,ListarEspecialistaEnviados.ResponseValue> {

    private EspecialistaEnviadosRepository especialistaEnviadosRepository;

    public ListarEspecialistaEnviados(EspecialistaEnviadosRepository especialistaEnviadosRepository) {
        this.especialistaEnviadosRepository = especialistaEnviadosRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        especialistaEnviadosRepository.mostrarListaClienteTodos(new EspecialistaEnviadosDataSource.CallBackResultado<List<EspecialistaEnviadosUi>>() {
            @Override
            public void onCallBackResultado(List<EspecialistaEnviadosUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues{

    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        List<EspecialistaEnviadosUi> especialistaEnviadosUiList;

        public ResponseValue(List<EspecialistaEnviadosUi> especialistaEnviadosUiList) {
            this.especialistaEnviadosUiList = especialistaEnviadosUiList;
        }

        public List<EspecialistaEnviadosUi> getClienteTodosUiList() {
            return especialistaEnviadosUiList;
        }
    }
}
