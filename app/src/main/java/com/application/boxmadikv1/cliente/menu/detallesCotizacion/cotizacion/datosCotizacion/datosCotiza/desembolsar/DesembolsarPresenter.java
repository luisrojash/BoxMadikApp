package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosCotiza.desembolsar;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface DesembolsarPresenter extends BaseActivityPresenter<DesembolsarView>{
    void onClickFinalizarDesemBolso(float ratingValue, String editTextComentario);
}
