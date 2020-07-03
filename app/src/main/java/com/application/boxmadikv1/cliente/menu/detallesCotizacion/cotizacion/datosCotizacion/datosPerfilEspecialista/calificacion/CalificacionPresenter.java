package com.application.boxmadikv1.cliente.menu.detallesCotizacion.cotizacion.datosCotizacion.datosPerfilEspecialista.calificacion;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface CalificacionPresenter  extends BaseActivityPresenter<CalificacionView>{
    void onClickCalificar(float ratingValue, String editTextComentario);
}
