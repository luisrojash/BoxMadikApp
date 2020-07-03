package com.application.boxmadikv1.login.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.LoginResponse;

public interface LoginDataSource {

    interface CallbackResultado<T> {
        void onResultado(T resultado);
    }


    void onLoguear(String usuario, String clave, CallbackResultado<LoginResponse> booleanCallbackResultado);

    void onGuardarUsuario(LoginResponse.UsuarioResponse usuarioResponse, CallbackResultado<Boolean> booleanCallbackResultado);

    void onGuardarUsuariosOnline(String usuarioCodigo, String estado, String paisCodigo, CallbackResultado<DefaultResponse> defaultResponseCallbackResultado);
}
