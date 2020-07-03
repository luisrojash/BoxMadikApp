package com.application.boxmadikv1.login.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.login.dataSource.LoginDataSource;
import com.application.boxmadikv1.login.dataSource.LoginRepository;

public abstract class GuardarUsuariosOnline extends UseCase<GuardarUsuariosOnline.RequestValues,GuardarUsuariosOnline.ResponseValue>{

   /* private LoginRepository loginRepository;

    public GuardarUsuariosOnline(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        loginRepository.onGuardarUsuariosOnline(requestValues.getUsuarioCodigo(), requestValues.getPropuestaEstado(), requestValues.getPaisCodigo(),
                new LoginDataSource.CallbackResultado<DefaultResponse>() {
                    @Override
                    public void onResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }*/

    public static final class RequestValues implements UseCase.RequestValues{
        private String usuarioCodigo;
        private String estado;
        private String paisCodigo;

        public RequestValues(String usuarioCodigo, String estado, String paisCodigo) {
            this.usuarioCodigo = usuarioCodigo;
            this.estado = estado;
            this.paisCodigo = paisCodigo;
        }

        public String getUsuarioCodigo() {
            return usuarioCodigo;
        }

        public String getEstado() {
            return estado;
        }

        public String getPaisCodigo() {
            return paisCodigo;
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
