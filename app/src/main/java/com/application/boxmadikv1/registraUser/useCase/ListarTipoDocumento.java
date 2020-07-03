package com.application.boxmadikv1.registraUser.useCase;

import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.registraUser.dataSource.RegistrarUsuarioDataSource;
import com.application.boxmadikv1.registraUser.dataSource.RegistrarUsuarioRepository;
import com.application.boxmadikv1.registraUser.entidad.TipoDocumentoUi;

import java.util.List;

public class ListarTipoDocumento extends UseCase<ListarTipoDocumento.RequestValues,ListarTipoDocumento.ResponseValue> {


    public static final String TAG = ListarTipoDocumento.class.getSimpleName();

    private RegistrarUsuarioRepository registrarUsuarioRepository;

    public ListarTipoDocumento(RegistrarUsuarioRepository registrarUsuarioRepository) {
        this.registrarUsuarioRepository = registrarUsuarioRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        registrarUsuarioRepository.onListartTipoDocumento(requestValues.getTipoPaisId(),new RegistrarUsuarioDataSource.CallbackResultado<List<TipoDocumentoUi>>() {
            @Override
            public void onCallBackResultado(List<TipoDocumentoUi> resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }


    public static final class RequestValues implements UseCase.RequestValues{
        private String tipoPaisId;

        public RequestValues(String tipoPaisId) {
            this.tipoPaisId = tipoPaisId;
        }

        public String getTipoPaisId() {
            return tipoPaisId;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue{
        List<TipoDocumentoUi> documentoUiList;

        public ResponseValue(List<TipoDocumentoUi> documentoUiList) {
            this.documentoUiList = documentoUiList;
        }

        public List<TipoDocumentoUi> getDocumentoUiList() {
            return documentoUiList;
        }

        public void setDocumentoUiList(List<TipoDocumentoUi> documentoUiList) {
            this.documentoUiList = documentoUiList;
        }
    }
}
