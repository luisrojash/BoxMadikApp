package com.application.boxmadikv1.registraUser.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.registraUser.dataSource.RegistrarUsuarioDataSource;
import com.application.boxmadikv1.registraUser.dataSource.RegistrarUsuarioRepository;
import com.application.boxmadikv1.registraUser.entidad.TipoDocumentoUi;
import com.application.boxmadikv1.registraUser.entidad.TipoPaisUi;

import java.util.List;

public class ListarTipoPais extends UseCase<ListarTipoPais.RequestValues, ListarTipoPais.ResponseValue> {


    public static final String TAG = ListarTipoPais.class.getSimpleName();

    private RegistrarUsuarioRepository registrarUsuarioRepository;

    public ListarTipoPais(RegistrarUsuarioRepository registrarUsuarioRepository) {
        this.registrarUsuarioRepository = registrarUsuarioRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        registrarUsuarioRepository.onListartTipoPais(new RegistrarUsuarioDataSource.CallbackResultado<List<TipoPaisUi>>() {
            @Override
            public void onCallBackResultado(List<TipoPaisUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues {

    }

    public static final class ResponseValue implements UseCase.ResponseValue {
        List<TipoPaisUi> tipoPaiUis;

        public ResponseValue(List<TipoPaisUi> tipoPaiUis) {
            this.tipoPaiUis = tipoPaiUis;
        }

        public List<TipoPaisUi> getTipoPais() {
            return tipoPaiUis;
        }

        public void setDocumentoUiList(List<TipoPaisUi> tipoPaiUis) {
            this.tipoPaiUis = tipoPaiUis;
        }
    }
}
