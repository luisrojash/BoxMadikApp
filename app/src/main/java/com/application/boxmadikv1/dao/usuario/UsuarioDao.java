package com.application.boxmadikv1.dao.usuario;

import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.dao.BaseDao;
import com.application.boxmadikv1.modelo.Usuario;

public interface UsuarioDao extends BaseDao<Usuario>{
    boolean crearUsuario(LoginResponse.UsuarioResponse usuarioResponse);
}
