package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion;

import com.application.boxmadikv1.base.fragment.BaseFragmentPresenter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;

public interface DetallesCotizaPresenter extends BaseFragmentPresenter<DetallesCotizaView>{
    void onClickDetalleCotizaciones(CotizacionesUi cotizacionesUi);

    void onValidateInternet(Boolean internet, CotizacionesUi cotizacionesUi);
}
