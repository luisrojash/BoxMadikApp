package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.entidad.TipoBancoUi;

public interface EspecBancoPresenter extends BaseActivityPresenter<EspecBancoView>{
    void onKeyUser(String keyUser,String codigoPais);

    void onClickTipoBanco(TipoBancoUi tipoBancoUi);

    void onSpinnerTipoCuenta(String tipoRangoPrecioUi);

    void onClickGuardar(String numeroCuenta,String numeroCuentainte);

    void onCloseBank();
}
