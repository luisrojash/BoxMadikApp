package com.application.boxmadikv1.cliente.menu.detallesCotizacion;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface DetallesCotizacionView extends BaseActivityView<DetallesCotizacionPresenter> {

    void mostrarFragmentos(DetallesCotizacionUi detallesCotizacionUi);

    void mostrarDataInicial(String imagenRubro, String nombrePropuesta, String fechaPropuesta,String nombreDepartamento);

    void mostrarDialogConfirmacion(List<CotizacionesUi> cotizacionesUis, String mensaje,String tipoEstadoEliminar);

    void mostrarMensaje(String message);

    void initStartActivityMenuCliente(String tipoEstado);

    void mostrarImagenEliminar();

    void ocultarImagenEliminar();
}
