package com.application.boxmadikv1.especialista.menu;

import com.application.boxmadikv1.base.activity.BaseActivityView;

import java.util.ArrayList;

public interface MenuEspecialistaView extends BaseActivityView<MenuEspecialistaPresenter> {

    void mostrarFragmentos(String keyUser,String codigoPais);

    void mostrarFab();

    void ocultarFab();

    void mostrarBuscador();

    void ocultarBuscador();

    void startBuscarPropuestaActivity(ArrayList<String> listIdRubros);

    void initStartActivitySeleccionUser();

    void mostrarMensaje(String propuesta_creada_correctamente);

    void mostrarDialogDireccion(String mensaje);


    void actualizarListasAceptoCotiza();

    void mostrarNotificacion(String tituloChat, String mensajeChat);
}
