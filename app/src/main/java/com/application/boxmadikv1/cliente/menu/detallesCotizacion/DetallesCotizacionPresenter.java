package com.application.boxmadikv1.cliente.menu.detallesCotizacion;


import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface DetallesCotizacionPresenter extends BaseActivityPresenter<DetallesCotizacionView> {
    void onObtenerListaPorEstado(List<CotizacionesUi> cotizacionesUis);

    void onEliminarPropuesta();

    void onEliminarPropuestaAceptado(List<CotizacionesUi> cotizacionesUis, String tipoEstadoEliminar);
}
