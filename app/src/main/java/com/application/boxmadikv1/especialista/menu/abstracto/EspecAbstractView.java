package com.application.boxmadikv1.especialista.menu.abstracto;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;

import java.util.List;

public interface EspecAbstractView extends BaseActivityView<EspecAbstractPresenter> {
    void mostrarMensaje(String s);

    void mostraListaEspecialistaEstados(List<EspecialistaEstadosUi> especialistaEstadosUis);

    void mostrarDialogConfirmacionDelete(EspecialistaEstadosUi especialistaEstadosUi, String mensaje);

    void startActivityPerfilPropuesta(ItemUi itemUi);

    void eliminarItem(EspecialistaEstadosUi especialistaEstadosUi);

    void actualizarListas();

    void mostrarDialogMensaje(String s);

    void initStartActivityCalifica(ItemUi itemUi, String nombreUsu, String usuPaisImagenCliente, String usuFotoCliente);

    void initStartActivityBancoDatos();

    void mostrarDialogCentroBancario();

    void mostrarTextViewEmpty();

    void ocultarTextViewEmpty();
}
