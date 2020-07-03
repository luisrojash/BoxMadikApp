package com.application.boxmadikv1.chat;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.chat.entidad.MensajesUi;

import java.util.List;

public interface ChatView extends BaseActivityView<ChatPresenter> {
    void mostrarDatos(String nombre, String foto, String tipoUsuario, String imagenRubro, String nombrePropuesta);

    void finishActivity();

    void agregarMensaje(MensajesUi mensajesUi);

    void mostrarLista(List<MensajesUi> mensajesUiList);

    void mostrarListaAdd(List<MensajesUi> mensajesUiList);

    void habilitarButtonEnviar();

    void deshabilitarButtonEnviar();
}
