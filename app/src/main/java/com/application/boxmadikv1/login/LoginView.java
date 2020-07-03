package com.application.boxmadikv1.login;

import com.application.boxmadikv1.base.activity.BaseActivityView;

public interface LoginView extends BaseActivityView<LoginPresenter> {

    void mostrarMensaje(String mensaje);

    void mostrarErrorUsuarioCampoVacio(String mensajeError);

    void mostrarErrorClaveCampoVacio(String mensajeError);

    void startActivitySeleccionUser(String usuarioCodigo,String usuarioDni, String usuNombre,
                                    String usuaApellidos,String usuCelular,String usuEmail,String usuFoto,String codigoPais,String tipoDocumento);

    void mostrarDialog();

    void ocultarDialog();

    void mostrarMensajeToast(String mensaje);

    /*Chequear Estados Internet*/

    void isConectadoInternet();

    void isDesconectadoInternet();
}
