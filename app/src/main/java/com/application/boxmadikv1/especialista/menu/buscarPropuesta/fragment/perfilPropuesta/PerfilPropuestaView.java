package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;

public interface PerfilPropuestaView extends BaseActivityView<PerfilPropuestaPresenter> {
    void mostrarFragmentos(ItemUi itemUi);

    void mostrarDataInicial(ItemUi itemUi);

    void iniStartActivityMenuEspecialista();

    void finishActivity();

    void starActivityChat(String tipoEstadoGrupo, String idUsuarioPropuesta, String keyUser,
                          String idPropuesta, String idGrupoChat,String tipoUsuario,String imagenRubro,String nombrePropuesta );

    void habilitarButtonChat();
    
    void deshabilitarButtonChat();

    void mostrarMensaje(String mensajeButton);

    void mostrarDialogInternet();

    void ocultarDialogInternet();

    void mostrarFabButtonChat();

    void ocultarFabButtonChat();

    void mostrarDialogDireccion(String mensaje);
}
