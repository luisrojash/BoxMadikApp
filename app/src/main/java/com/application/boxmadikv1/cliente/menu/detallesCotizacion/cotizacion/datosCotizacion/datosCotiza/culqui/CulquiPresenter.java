package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.culqui;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface CulquiPresenter extends BaseActivityPresenter<CulquiView>{
    void onClickSiguiente(String numberCard,String numberCvv,String email,String anio,String mes);

    void onClickDepositar();

    void onPreferencesToken(String idToke);
}
