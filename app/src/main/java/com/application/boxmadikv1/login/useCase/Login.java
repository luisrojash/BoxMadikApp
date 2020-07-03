package com.application.boxmadikv1.login.useCase;

import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.base.UseCase;
import com.application.boxmadikv1.login.dataSource.LoginDataSource;
import com.application.boxmadikv1.login.dataSource.LoginRepository;

public class Login extends UseCase<Login.RequestValues, Login.ResponseValue> {

    private LoginRepository loginRepository;

    public Login(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        loginRepository.onLoguear(requestValues.getEmail(), requestValues.getClave(), new LoginDataSource.CallbackResultado<LoginResponse>() {
            @Override
            public void onResultado(LoginResponse resultado) {
                getUseCaseCallback().onSuccess(new ResponseValue(resultado));
            }
        });
    }

    public static final class RequestValues implements UseCase.RequestValues {
        private String email;
        private String clave;

        public RequestValues(String email, String clave) {
            this.email = email;
            this.clave = clave;
        }

        public String getEmail() {
            return email;
        }

        public String getClave() {
            return clave;
        }
    }

    public static final class ResponseValue implements UseCase.ResponseValue {
       private LoginResponse resultado;

        public ResponseValue(LoginResponse resultado) {
            this.resultado = resultado;
        }

        public LoginResponse getResultado() {
            return resultado;
        }
    }
}
