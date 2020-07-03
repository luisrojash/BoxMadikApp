package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza;

import android.view.View;

import com.application.boxmadikv1.base.fragment.BaseFragmentPresenter;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.entidad.CotizacionesUi;
import com.application.boxmadikv1.cliente.menu.detallesCotizacion.entidad.DetallesCotizacionUi;

public interface DatosCotizaPresenter extends BaseFragmentPresenter<DatosCotizaView> {
    void onClickAceptart();

    void onClickEnviarRevocacion();

    void onClickDesembolsar();

    void onClickLevantarRevocacion();

    void onClickMensaje();

    void onClickReportePago();

    void onClickBtnVerRespuesta();

    void onClickCoonfirmarLevantarRevoca(DetallesCotizacionUi detallesCotizacionUi, CotizacionesUi cotizacionesUi);
}
