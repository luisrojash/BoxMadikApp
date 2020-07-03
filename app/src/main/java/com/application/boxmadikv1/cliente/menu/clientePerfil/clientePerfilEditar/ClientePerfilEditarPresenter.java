package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar;

import android.content.Intent;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface ClientePerfilEditarPresenter extends BaseActivityPresenter<ClientePerfilEditarView> {


    void onInitKeyUser(String usuarioCodigo,String usuarioDni,String usuarioNombre,String usuarioApellidos,
                       String usuarioCelular,String usuarioEmail,String usuarioFoto,String usuarioPais,String usuarioTipoDoc);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void OnClickPerfilDireccion(String nombreEdit, String apellidosEdit, String celularEdit);
}
