package com.application.boxmadikv1.rptRevocacion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.maps.MapsActivity;
import com.application.boxmadikv1.rptRevocacion.entidad.PropuestaRevocacionUi;

import java.util.List;

public interface RespuestaRevocacionView extends BaseActivityView<RespuestaRevocacionPresenter> {
    void mostrarListaPropuestaRevocacion(List<PropuestaRevocacionUi> documentoUiList);

    void mostrarCheckSi();

    void ocultarCheckSi();

    void mostrarCheckNo();

    void ocultarCheckNo();

    void mostrarMensajeErroDetalles(String mensaje);

    void habilitarButtonEnviar();

    void deshabilitarButtonEnviar();

    void mostrarMensaje(String mensaje);

    void ocultarProgressBarDialog();

    void starMainActivityEspec();

    void mostrarProgressBarDialog(String mensaje);

    void mostrarDataObtenida(String descripcion_resp, String observa_resp);

    void enviarMenuPrinicipal();

    void deshabilitarWidgetCompleto();


}
