package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.EstudioPerfilDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.EstudioPerfilRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;

import java.util.List;

public class ListarTipoEstudios extends UseCase<ListarTipoEstudios.RequestValues, ListarTipoEstudios.ResponseValue> {

    private EstudioPerfilRepository estudioPerfilRepository;

    public ListarTipoEstudios(EstudioPerfilRepository estudioPerfilRepository) {
        this.estudioPerfilRepository = estudioPerfilRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        estudioPerfilRepository.onListarTipoEstudios(new EstudioPerfilDataSource.CallBackResultado<List<TipoEstudiosUi>>() {
            @Override
            public void onCallBackResultado(List<TipoEstudiosUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoEstudiosUi> tipoEstudiosUiList;

        public ResponseValue(List<TipoEstudiosUi> tipoEstudiosUiList) {
            this.tipoEstudiosUiList = tipoEstudiosUiList;
        }

        public List<TipoEstudiosUi> getTipoEstudiosUiList() {
            return tipoEstudiosUiList;
        }
    }
}
