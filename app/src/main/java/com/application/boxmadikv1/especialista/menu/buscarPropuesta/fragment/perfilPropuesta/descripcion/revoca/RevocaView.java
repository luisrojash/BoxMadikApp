package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revoca;

import com.application.boxmadikv1.api.response.cliente.ObtenerRespuestaRevocaEspResponse;
import com.application.boxmadikv1.base.activity.BaseActivityView;

public interface RevocaView extends BaseActivityView<RevocaPresenter>{

    void mostrarProgressBarDialog(String mensaje);

    void mostrarDataInicial(String nombrePropuesta, String imagenRubro);

    void mostrarDataCargada(ObtenerRespuestaRevocaEspResponse.ObtenerRevocaEspResponse obtenerRevocaEspResponse);

    void deshabilitarWidget();
}
