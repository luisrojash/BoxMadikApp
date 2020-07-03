package com.application.boxmadikv1.recuperarClave;

import com.application.boxmadikv1.base.activity.BaseActivityView;

public interface RecuperarClaveView extends BaseActivityView<RecuperarClavePresenter> {


    void mostrarErrorCorreoCampoVacio(String mensajeError);

    void mostrarProgressDialog();

    void ocultarProgressDialog();

    void mostrarMensaje(String mensaje,String estado);

    void finishActivity();
}
