package com.application.boxmadikv1.registraUser;

import android.net.Uri;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.registraUser.entidad.TipoDocumentoUi;
import com.application.boxmadikv1.registraUser.entidad.TipoPaisUi;

import java.util.List;

public interface RegistrarUsuarioView extends BaseActivityView<RegistrarUsuarioPresenter> {

    void mostrarImagenSubida(Uri uri);

    void mostrarMensaje(String mensaje);

    void mostrarListaSpinnerTipoDocumento(List<TipoDocumentoUi> tipoDocumentoUiList);

    void mostrarListaSpinnerTipoPais(List<TipoPaisUi> tipoPaisUiList);

    void mostrarErrorEditTextTipoDocumento(String mensaje);

    void mostrarErrorEditTextNombre(String mensaje);

    void mostrarErrorEditTextApellidos(String mensaje);

    void mostrarErrorEditTextEmail(String mensaje);

    void mostrarErrorEditTextCelular(String mensaje);

    void mostrarProgressBarDialog();

    void ocultarProgressBarDialog();

    void startLoginActivity(String estado);

    void mostrarCheckTrue();

    void mostrarCheckFalse();

    void editTextTipoDocumentoValidar(String tipoDocumento);

    void initSpinnerAdapter();

    void initStartActivitytTerminosRegistrosUser(int terminosCondicionesRegistrarUser);

    void mostrarPositionPeru(int position);

    void mostrarEditTextNombre();

    void ocultarEditTextNombre();

    void mostrarEditTextApellido();

    void ocultarEditTextApellido();

    void mostrarEditTextRazonSocial();

    void ocultarEdiTextRazonSocial();

    void mostrarEditTextFechaNacimiento();

    void ocultarEditTextFechaNacimiento();

    void mostrarErrorEditTextDia(String mensaje);

    void mostrarErrorEditTextMes(String mensaje);

    void mostrarErrorEditTextAnio(String mensaje);

    void mostrarErrorEditTextRazonSocial(String ingrese_razon_social);

    void habilitarButtonRegistrar();

    void deshabilitarButtonRegistrar();

    void obtenerAnioActual();
}
