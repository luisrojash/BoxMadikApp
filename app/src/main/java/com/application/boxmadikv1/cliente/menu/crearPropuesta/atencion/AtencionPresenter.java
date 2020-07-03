package com.application.boxmadikv1.cliente.menu.crearPropuesta.atencion;

import android.content.Intent;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface AtencionPresenter extends BaseActivityPresenter<AtencionView> {

    void onSpinnerTipoPrecio(String idPrecio,int posicion);

    void onSpinnerTipoTurno(String idTurno, int posicion);

    void onSpinnerTipoDias(String idDias,int posicion);

    void onClickSiguiente();

    void validarTipoPrecio();

    void validarTipoDias();

    void validarTipoTurno();

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onNewIntent(Intent intent);

    void onFinishActivity();
}
