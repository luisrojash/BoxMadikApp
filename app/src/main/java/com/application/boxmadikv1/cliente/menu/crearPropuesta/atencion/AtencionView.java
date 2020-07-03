package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoDiasUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoPrecioUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion.entidad.TipoRangoTurnoUi;

import java.util.ArrayList;
import java.util.List;

public interface AtencionView extends BaseActivityView<AtencionPresenter> {

    void mostrarListaTipoPrecio(List<TipoRangoPrecioUi> tipoRangoPrecioUis);

    void mostrarListaTipoDias(List<TipoRangoDiasUi> tipoRangoDiasUis);

    void mostrarListaTipoTurno(List<TipoRangoTurnoUi> tipoRangoTurnoUis);

    void mostrarMensaje(String mensaje);

    void startActivityDetalles(int rubroId, int oficioId, String tipoTurno, String tipoPrecio, String tipoDias,String imageRubro,String nombreOficio, int posicionTipoPrecio,int  posicionTipoTurno, int posicionTipoDias, ArrayList<String> listaIdEspecialistas);

    void mostrarCabecera(String imageRubro, String nombreOficio);

    void mostrarSpinnerSeleccionTipoPrecio(int onBackposicionTipoPrecio);

    void mostrarSpinnerSeleccionTipoDias(int onBackposicionTipoDias);

    void mostrarSpinnerSeleccionTipoturno(int onBackposicionTipoTurno);

    void onBackActivityEspec(int posicionTipoPrecio, int posicionTipoTurno, int posicionTipoDias);
}
