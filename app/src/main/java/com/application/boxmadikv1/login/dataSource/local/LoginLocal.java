package com.application.boxmadikv1.login.dataSource.local;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.dao.usuario.UsuarioDao;
import com.application.boxmadikv1.login.dataSource.LoginDataSource;

public class LoginLocal implements LoginDataSource {

    private UsuarioDao usuarioDao;

    public LoginLocal(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }

    @Override
    public void onLoguear(String usuario, String clave, CallbackResultado<LoginResponse> booleanCallbackResultado) {

    }

    @Override
    public void onGuardarUsuario(LoginResponse.UsuarioResponse usuarioResponse, CallbackResultado<Boolean> booleanCallbackResultado) {
        boolean resultado = usuarioDao.crearUsuario(usuarioResponse);
        booleanCallbackResultado.onResultado(resultado);
    }

    @Override
    public void onGuardarUsuariosOnline(String usuarioCodigo, String estado, String paisCodigo, CallbackResultado<DefaultResponse> defaultResponseCallbackResultado) {

    }
}
