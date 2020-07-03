package com.application.boxmadikv1.login.useCase;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.login.dataSource.LoginDataSource;
import com.application.boxmadikv1.login.dataSource.LoginRepository;

public class UsuariosOnline extends GuardarUsuariosOnline {

    private LoginRepository loginRepository;

    public UsuariosOnline(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    @Override
    protected void executeUseCase(RequestValues requestValues) {
        loginRepository.onGuardarUsuariosOnline(requestValues.getUsuarioCodigo(), requestValues.getEstado(), requestValues.getPaisCodigo(),
                new LoginDataSource.CallbackResultado<DefaultResponse>() {
                    @Override
                    public void onResultado(DefaultResponse resultado) {
                        getUseCaseCallback().onSuccess(new ResponseValue(resultado));
                    }
                });
    }
}
