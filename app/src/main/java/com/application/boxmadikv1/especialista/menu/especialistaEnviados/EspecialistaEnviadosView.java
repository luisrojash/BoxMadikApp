package com.application.boxmadikv1.especialista.menu.especialistaEnviados;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;

import java.util.List;

public interface EspecialistaEnviadosView extends BaseActivityView<EspecialistaEnviadosPresenter> {

    void mostraListaEspecialistaEnviados(List<EspecialistaEnviadosUi>especialistaEnviadosUiList );

    void mostrarMensaje(String s);

    void mostrarDialogConfirmacionDelete(EspecialistaEnviadosUi especialistaEnviadosUi,String mensaje);

    void eliminarItem(EspecialistaEnviadosUi especialistaEnviadosUi);

    void startActivityPerfilPropuesta(ItemUi itemUi);
}
