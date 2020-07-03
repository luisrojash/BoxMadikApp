package com.application.boxmadikv1.rptRevocacion;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.rptRevocacion.entidad.PropuestaRevocacionUi;

public interface RespuestaRevocacionPresenter extends BaseActivityPresenter<RespuestaRevocacionView> {
    void onSpinnerTipoPropuestaRevocacion(String propuestaRevocacionUiID );

    void onClickButtonSi();

    void onClickButtonNo();

    void onClickEnviarRespuesta(String detalleRespuesta);
}
