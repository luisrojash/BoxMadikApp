package com.application.boxmadikv1.seleccionUsuario;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.sesion.TinyDB;

public interface SeleccionUserPresenter extends BaseActivityPresenter<SeleccionUserView> {

    void onClickCliente(String tipoCliente);

    void onClickEspecialista();

    void onClickSiguiente();


    void onValidateSeleccionRubros(TinyDB tinydb, String tipoCliente, String departamentoId);

    void onDatosPreferencias(String codigoUsuario, String paisCodigo, String tokeUsuario);

    void onLogoutUser();

    void onRegistroToken(String newToken);

    void onClickNotificacionCliente();

    void onClickNotificacionEspecialista();

}
