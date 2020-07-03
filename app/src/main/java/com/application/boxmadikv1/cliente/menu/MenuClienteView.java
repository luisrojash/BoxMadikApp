package com.application.boxmadikv1.cliente.menu;

import com.application.boxmadikv1.base.activity.BaseActivityView;

public interface MenuClienteView extends BaseActivityView<MenuClientePresenter> {

    void mostrarFragmentos(String keyUser,String codigoPais);

    void mostrarFab();

    void ocultarFab();

    void mostrarBuscador();

    void ocultarBuscador();

    void mostrarContenidoPagerTransparente();

    void ocultarContenidoPagerTransparente();

    void starActivityRubros();

    void mostrarMensaje(String propuesta_creada_correctamente);

    void starActivityPerfil();

    void obteniendoKeyUser();

    void initStartActivitySeleccionUser();

    void mostrarDialogDireccion(String mensaje);

    void mostrarNotificacion(String tituloChat, String mensajeChat);

    void actualizarFragmentoCotizacionPendiente();
}
