package com.application.boxmadikv1.registraUser;

import android.content.Intent;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.registraUser.entidad.TipoPaisUi;
import com.application.boxmadikv1.registraUser.entidad.UserUi;

import java.util.List;

public interface RegistrarUsuarioPresenter extends BaseActivityPresenter<RegistrarUsuarioView> {

    void onRegistrarUsuario(UserUi userUi);

    void onActivityResult(int requestCode, int resultCode, Intent data);

    void onSpinnerTipoDocumento(String tipoDocumento);

    void onSpinnerTipoPais(String tipoPais);

    void onAceptarTerminosCondiciones();

    void onCheckBoxTerminos();

    void onFilterPeru(List<TipoPaisUi> tipoPaisUiList);

    void onCumple(int year, int month, int dayOfMonth);


    void onObtenerAnioActual(String obtenerAnioActual);
}
