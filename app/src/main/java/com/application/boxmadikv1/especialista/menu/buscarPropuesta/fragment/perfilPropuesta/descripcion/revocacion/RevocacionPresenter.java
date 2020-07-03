package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.revocacion;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface RevocacionPresenter extends BaseActivityPresenter<RevocacionView> {

    void onProgressChanged(int value);

    void onSpinnerTipoSolucionRevocacion(String idTipSolucionRevocacion);

    void onSpinnerTipoMotivoRevocacion(String idTipoMotivoRevocacion);

    void onClickEnviarRevocacion(String observacion);
}
