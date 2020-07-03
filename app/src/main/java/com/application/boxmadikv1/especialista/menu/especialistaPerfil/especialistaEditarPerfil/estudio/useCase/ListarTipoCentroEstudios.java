package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.EstudioPerfilDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.EstudioPerfilRepository;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;

import java.util.List;

public class ListarTipoCentroEstudios  extends UseCase<ListarTipoCentroEstudios.RequestValues,ListarTipoCentroEstudios.ResponseValue>{

    private EstudioPerfilRepository estudioPerfilRepository;

    public ListarTipoCentroEstudios(EstudioPerfilRepository estudioPerfilRepository) {
        this.estudioPerfilRepository = estudioPerfilRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        estudioPerfilRepository.onListarTipoCentroEstudios(requestValues.getPaisCodigo(),new EstudioPerfilDataSource.CallBackResultado<List<TipoCentroEstudiosUi>>() {
            @Override
            public void onCallBackResultado(List<TipoCentroEstudiosUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String paisCodigo;

        public RequestValues(String paisCodigo) {
            this.paisCodigo = paisCodigo;
        }

        public String getPaisCodigo() {
            return paisCodigo;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        private List<TipoCentroEstudiosUi> tipoCentroEstudiosUiList;

        public ResponseValue(List<TipoCentroEstudiosUi> tipoCentroEstudiosUiList) {
            this.tipoCentroEstudiosUiList = tipoCentroEstudiosUiList;
        }

        public List<TipoCentroEstudiosUi> getTipoCentroEstudiosUiList() {
            return tipoCentroEstudiosUiList;
        }
    }
}
