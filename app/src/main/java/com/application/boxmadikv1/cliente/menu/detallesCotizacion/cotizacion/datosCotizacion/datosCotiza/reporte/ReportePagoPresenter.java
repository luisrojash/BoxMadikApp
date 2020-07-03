package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.reporte;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface ReportePagoPresenter extends BaseActivityPresenter<ReportePagoView> {
    void onClickBtnFinalizar();

    void onClickAceptoReportePago();

    void onBackPresseDatosCotiza();
}
