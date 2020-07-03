package com.application.boxmadikv1.recuperarClave;

import com.application.boxmadikv1.base.activity.BaseActivity;
import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface RecuperarClavePresenter extends BaseActivityPresenter<RecuperarClaveView> {
    void onEnviarCorreo(String correo);
}
