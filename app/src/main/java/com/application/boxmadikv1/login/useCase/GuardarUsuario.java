package com.application.boxmadikv1.login.useCase;

import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.login.dataSource.LoginDataSource;
import com.application.boxmadikv1.login.dataSource.LoginRepository;

public class GuardarUsuario extends UseCase<GuardarUsuario.RequestValues, GuardarUsuario.ResponsValue> {

    private LoginRepository loginRepository;

    public GuardarUsuario(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        loginRepository.onGuardarUsuario(requestValues.getUsuarioResponse(), new LoginDataSource.CallbackResultado<Boolean>() {
            @Override
            public void onResultado(Boolean resultado) {
                getUseCaseCallback().onSuccess(new ResponsValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private LoginResponse.UsuarioResponse usuarioResponse;

        public RequestValues(LoginResponse.UsuarioResponse usuarioResponse) {
            this.usuarioResponse = usuarioResponse;
        }

        public LoginResponse.UsuarioResponse getUsuarioResponse() {
            return usuarioResponse;
        }
    }

    public static final class ResponsValue implements UseCase.ResponseValue {
        private boolean aBoolean;

        public ResponsValue(boolean aBoolean) {
            this.aBoolean = aBoolean;
        }

        public boolean isaBoolean() {
            return aBoolean;
        }

        public void setaBoolean(boolean aBoolean) {
            this.aBoolean = aBoolean;
        }
    }
}
