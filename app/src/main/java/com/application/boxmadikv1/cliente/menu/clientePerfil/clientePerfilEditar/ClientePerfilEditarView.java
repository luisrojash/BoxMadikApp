package com.application.boxmadikv1.cliente.menu.clientePerfil.clientePerfilEditar;

import android.net.Uri;

import com.application.boxmadikv1.base.activity.BaseActivityView;

public interface ClientePerfilEditarView extends BaseActivityView<ClientePerfilEditarPresenter> {

    void starActivityPerfilDireccion(String nombreEdit, String apellidosEdit, String celularEdit, String usuarioFoto);

    void obteniendoKeyUser();

    void mostrarDatosInit(String usuarioDni,String usuarioNombre,String usuarioApellidos,String usuarioCelular,
                          String usuarioEmail,String usuarioFoto);

    void mostrarPaisNombre(String nombrePais);

    void mostrarTipoDocNombre(String tipoDocNombre);

    void mostrarImageUsuario(Uri uriComprimida );

    void mostrarMensaje(String mensaje);

    void mostrarProgressBarDialog();

    void ocultarProgressBarDialog();

    void actualizarDataPreferencesConFoto(String nombreEdit, String apellidoEdit, String celularEdit, String usuFoto);

    void startActivityPerfil();

    void actualizarDataPreferencesSinFoto(String nombreEdit, String apellidoEdit, String celularEdit);

    void habilitarButtonGuardar();

    void deshabilitarButtonGuardar();
}
