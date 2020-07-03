package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar;

import android.content.Intent;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.ClientPerfilDirectionActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.ClientePerfilDireccionActivity;

public class ClientePerfilEditActivity extends ClientePerfilEditarActivity {

    public static final String EXTRA_TIPO_ESTADO_PERFIL = "estado";

    @Override
    public void starActivityPerfilDireccion(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFoto) {
        String tipoEstado = "estadoCliente";
        Intent intent = new Intent(this, ClientPerfilDirectionActivity.class);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_NOMBRE, nombreEdit);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_APELLIDOS, apellidosEdit);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_CELULAR, celularEdit);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_FOTO, usuarioFoto);
        intent.putExtra(EXTRA_TIPO_ESTADO_PERFIL,tipoEstado);
        startActivity(intent);

    }


}
