package com.application.boxmadikv1.login;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface LoginPresenter extends BaseActivityPresenter<LoginView> {

    void onClickLogin(String usuario, String clave);

    void validarConexion(Boolean estado);
}
