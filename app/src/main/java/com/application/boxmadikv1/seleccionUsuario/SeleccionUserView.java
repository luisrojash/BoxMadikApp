package com.application.boxmadikv1.seleccionUsuario;

import com.application.boxmadikv1.base.activity.BaseActivityView;

import java.util.ArrayList;

public interface SeleccionUserView extends BaseActivityView<SeleccionUserPresenter> {

    void mostrarCheckCliente();

    void mostrarCheckEspecialista();

    void mostrarMensaje(String mensaje);

    void startMenuCliente(String tipoUsuarioId);

    void startMenuEspecialista(ArrayList<String> arrayList);

    void startSeleccionRubros(String tipoUsuario);


    void limpiarPreferencias();

    void startActivityMenuCliente();

    void startActivityDistritoTrabajo(String tipoInicial);

    void actualizarToken(String newToken);

    void initStartActivityNotificacionCliente(String codigoUsuario, String paisCodigo, String tipoNotificacion);

    void initStartActivityNotificacionEspecialista(String codigoUsuario, String paisCodigo, String tipoNotificacion);

    void mostrarConteoEspecialista(String conteoEspecialista);

    void mostrarConteoCliente(String conteoCliente);

    void onGuardarDataEnPreferencesListaRubros(ArrayList<String> stringListIdRubros);

    void onGuardarDataEnPreferencesDepartamentoId(String codigoDepartamento);

    void habilitarButtonEspec();

    void deshabilitarButtonEspec();
}
