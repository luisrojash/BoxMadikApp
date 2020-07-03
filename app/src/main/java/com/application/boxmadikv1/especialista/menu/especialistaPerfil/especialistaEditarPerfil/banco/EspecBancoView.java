package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosBancaria;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.entidad.TipoBancoUi;

import java.util.List;

public interface EspecBancoView extends BaseActivityView<EspecBancoPresenter> {
    void mostrarListaTipoBancio(List<TipoBancoUi> tipoBancoUiList);

    void mostrarTextViewBanco(String descripcion);

    void mostrarListaTipoCuenta(List<String> stringListTipoCuenta);

    void mostrarMensajeErrorBancoText(String mensaje);

    void mostrarMensaje(String mensaje);

    void mostrarMensajeErrorNumeroText(String mensaje);

    void mostrarMensajeErrorNumeroInterbakText(String mensaje);

    void initStartEditActivityPerfil(String extraCorrectamenteEspecBanco);

    void mostrarDataInicial(DatosBancaria datosBancaria);

    void deshabilitarTextBank();

    void limpiarTextBank();

    void mostrarDialogProgress();

    void ocultarDialogProgress();

    void habilitarButtonGuardar();

    void deshabilitarButtonGuardar();

    void mostrarSpinnerLleno(int posicion);
}
