package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion;

import android.content.Intent;

import com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar.clientePerfilDireccion.ClientePerfilDireccionActivity;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.direccion.especialistaPerfilDistrito.EspecialistaPerfilDistritoActivity;

public class EspecPerfilDireccionActivity extends ClientePerfilDireccionActivity {

    public static final String EXTRA_DIRECCION_NOMBRE_EDIT = "nombreEdit";
    public static final String EXTRA_DIRECCION_APELLIDOS_EDIT = "apellidosEdit";
    public static final String EXTRA_DIRECCION_CELULAR_EDIT = "celularEdit";
    public static final String EXTRA_DIRECCION_USUARIO_FOTO_EDIT = "usuarioFoto";
    public static final String EXTRA_DIRECCION_CODIGO_DEPARTAMENTO_EDIT = "codigoDepartamento";
    public static final String EXTRA_DIRECCION_CODIGO_PROVINCIA_EDIT = "codigoProvincia";
    public static final String EXTRA_DIRECCION_CODIGO_DISTRITO_EDIT = "codigoDistrito";
    public static final String EXTRA_DIRECCION_CODIGO_LATITUD_EDIT = "latitud";
    public static final String EXTRA_DIRECCION_CODIGO_lONGITUD_EDIT = "longitud";
    public static final String EXTRA_DIRECCION_CODIGO_DESCRIPCION_DIRECCION = "descripcionDireccion";

    public static final String EXTRA_DIRECCION_EDIT_ESPEC = "EspecPerfilDireccionActivity";

    @Override
    public void initStartActivityDistritos(String nombreEdit, String apellidosEdit, String celularEdit,
                                           String usuarioFoto, String codigoDepartamento, String codigoProvincia,
                                           String codigoDistrito, double latitud, double longitud,
                                           String descripcionDireccion) {
        Intent intent = new Intent(this, EspecialistaPerfilDistritoActivity.class);
        intent.putExtra(EXTRA_DIRECCION_NOMBRE_EDIT, nombreEdit);
        intent.putExtra(EXTRA_DIRECCION_APELLIDOS_EDIT, apellidosEdit);
        intent.putExtra(EXTRA_DIRECCION_CELULAR_EDIT, celularEdit);
        intent.putExtra(EXTRA_DIRECCION_USUARIO_FOTO_EDIT, usuarioFoto);
        intent.putExtra(EXTRA_DIRECCION_CODIGO_DEPARTAMENTO_EDIT, codigoDepartamento);
        intent.putExtra(EXTRA_DIRECCION_CODIGO_PROVINCIA_EDIT, codigoProvincia);
        intent.putExtra(EXTRA_DIRECCION_CODIGO_DISTRITO_EDIT, codigoDistrito);
        intent.putExtra(EXTRA_DIRECCION_CODIGO_LATITUD_EDIT, latitud);
        intent.putExtra(EXTRA_DIRECCION_CODIGO_lONGITUD_EDIT, longitud);
        intent.putExtra(EXTRA_DIRECCION_CODIGO_DESCRIPCION_DIRECCION, descripcionDireccion);
        startActivity(intent);

    }
}
