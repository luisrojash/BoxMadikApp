package com.application.boxmadikv1.dao.usuario;

import com.application.boxmadikv1.api.response.LoginResponse;
import com.application.boxmadikv1.dao.BaseDaoImpl;
import com.application.boxmadikv1.modelo.Usuario;
import com.application.boxmadikv1.modelo.Usuario_Table;


public class UsuarioDaoImpl extends BaseDaoImpl<Usuario, Usuario_Table> implements UsuarioDao {


    private static UsuarioDaoImpl mInstance = null;

    public static final UsuarioDaoImpl getmInstance() {
        if (mInstance == null) {
            mInstance = new UsuarioDaoImpl();
        }
        return mInstance;
    }

    @Override

    protected Class<Usuario> getEntityClass() {
        return Usuario.class;
    }

    @Override
    protected Class<Usuario_Table> getTableclass() {
        return Usuario_Table.class;
    }

    @Override
    public boolean crearUsuario(LoginResponse.UsuarioResponse usuarioResponse) {
        boolean success = true;
        Usuario usuario = new Usuario();
        usuario.setUsu_Codigo(usuarioResponse.getUsu_Codigo());
        usuario.setUsu_DNI(usuarioResponse.getUsu_DNI());
        usuario.setUsu_Nom1(usuarioResponse.getUsu_Nom1());
        usuario.setUsu_Ape_Pat_Mate(usuarioResponse.getUsu_Ape_Pat_Mate());
        usuario.setUsu_Celular(usuarioResponse.getUsu_Celular());
        usuario.setUsu_Email(usuarioResponse.getUsu_Email());
        usuario.setUsu_Foto(usuarioResponse.getUsu_foto());
        usuario.setPais_Pais_Codigo(usuarioResponse.getPais_Pais_Codigo());
        usuario.setTDoc_Codigo(usuarioResponse.getTDoc_Codigo());
        usuario.setSyncFlag(Usuario.FLAG_ADDED);
        boolean succeUsuario = usuario.save();
        if (!succeUsuario) {
            return false;
        }
        return success;
    }
}
