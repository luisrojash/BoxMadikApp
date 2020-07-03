package com.application.boxmadikv1.splash;

import com.application.boxmadikv1.base.activity.BaseActivityView;

public interface SplashView extends BaseActivityView<SplashPresenter> {

    void startSeleccionarUserActivity();

    void mostrarDialogMensaje(String mensaje);
}
