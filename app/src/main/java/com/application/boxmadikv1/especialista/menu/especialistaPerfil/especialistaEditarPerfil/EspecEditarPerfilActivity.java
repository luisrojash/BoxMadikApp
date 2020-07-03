package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil;

import android.content.Intent;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.ClientePerfilEditarActivity;
import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.ClientPerfilDirectionActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.EspecPerfilDireccionActivity;

import static com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.ClientePerfilEditActivity.EXTRA_TIPO_ESTADO_PERFIL;

public class EspecEditarPerfilActivity extends ClientePerfilEditarActivity {

    @Override
    public void starActivityPerfilDireccion(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFoto) {
        String tipoEstado = "estadoEspecialista";
        Intent intent = new Intent(this, EspecPerfilDireccionActivity.class);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_NOMBRE, nombreEdit);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_APELLIDOS, apellidosEdit);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_CELULAR, celularEdit);
        intent.putExtra(EXTRA_CLIENTE_PERFIL_EDITAR_FOTO, usuarioFoto);
        intent.putExtra(EXTRA_TIPO_ESTADO_PERFIL,tipoEstado);
        startActivity(intent);
    }
}
