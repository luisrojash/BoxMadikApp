package com.application.boxmadikv1.login.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.login.dataSource.local.LoginLocal;
import com.application.boxmadikv1.login.dataSource.remote.LoginRemote;

public class LoginRepository implements LoginDataSource {

    private LoginRemote loginRemote;
    private LoginLocal loginLocal;
    private static LoginRepository mInstance = null;

    public static final LoginRepository getmInstance(LoginRemote loginRemote, LoginLocal loginLocal) {
        if (mInstance == null) {
            mInstance = new LoginRepository(loginRemote,loginLocal);
        }
        return mInstance;
    }

    public LoginRepository(LoginRemote loginRemote, LoginLocal loginLocal) {
        this.loginRemote = loginRemote;
        this.loginLocal = loginLocal;
    }

    @Override
    public void onLoguear(String usuario, String clave, CallbackResultado<LoginResponse> booleanCallbackResultado) {
        loginRemote.onLoguear(usuario, clave, booleanCallbackResultado);
    }

    @Override
    public void onGuardarUsuario(LoginResponse.UsuarioResponse usuarioResponse, CallbackResultado<Boolean> booleanCallbackResultado) {
        loginLocal.onGuardarUsuario(usuarioResponse, booleanCallbackResultado);
    }

    @Override
    public void onGuardarUsuariosOnline(String usuarioCodigo, String estado, String paisCodigo, CallbackResultado<DefaultResponse> defaultResponseCallbackResultado) {
        loginRemote.onGuardarUsuariosOnline(usuarioCodigo, estado, paisCodigo, defaultResponseCallbackResultado);
    }
}
