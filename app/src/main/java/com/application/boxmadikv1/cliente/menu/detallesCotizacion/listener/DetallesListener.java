package com.application.boxmadikv1.cliente.menu.detallesCotizacion.listener;

import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

import java.util.List;

public interface DetallesListener {

    void ObternerListaPorEstado(List<CotizacionesUi>etallesCotizacionUiList);
}
