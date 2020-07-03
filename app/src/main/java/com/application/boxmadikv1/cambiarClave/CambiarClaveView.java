package com.application.boxmadikv1.cambiarClave;

import com.application.boxmadikv1.base.activity.BaseActivityView;

public interface CambiarClaveView extends BaseActivityView<CambiarClavePresenter>{
    void mostrarMensaje(String s);

    void mostrarProgressBarDialog();

    void ocultarProgressBarDialog();

    void finishActivity();

    void habilitarButtonGuardar();

    void deshabilitarButtonGuardar();

}
