package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion;

import android.text.Editable;

import com.application.boxmadikv1.api.response.especialista.DatosCotizacionResponse;
import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.entidad.ItemUi;

public interface CotizacionView extends BaseActivityView<CotizacionPresenter> {
    void mostrarDialogFechaInicio();

    void mostrarDialogFechaFin();

    void mostrarDataInicial(ItemUi itemUi);

    void editTextComision(String resultado);

    void editTextTotalSoles(String resultado);

    void ediTextTotalDolares(String resultado);

    void mostrarMensajeEditTextMontoError(String s);

    void mostrarMensajeEditTextDescripcionError(String s);

    void mostrarMensaje(String elija_la_fecha_inicial);

    void mostrarDialogProgressBar();

    void ocultarDialogProgressBar();

    void limpiarTexto();

    void mostrarCamposLlenos(String montoNeto, String montoComision, String totalSoles, String totalDolares, String fechaInicio, String fechaFin, String descripcion);

    void initStartActivityEspec();

    void initStartActivitySeleccionarUser();

    void mostrarDialogMensaje(String s);

    void habilitarButtonCotizacion();

    void deshabilitarButtonCotizacion();

    void deshabilitarButtonOficioPersonal();

    void habilitartButtonOficioPersonal();

    void mostrareditTextMontoNeto(String montoNeto);

    void textViewMostrarInformacion(String fecha_de_la_entrevista);

    void mostrarMensajeEditTextMontoTotalSolesError(String s);
}
