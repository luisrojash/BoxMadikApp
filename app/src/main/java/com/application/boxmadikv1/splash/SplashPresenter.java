package com.application.boxmadikv1.splash;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface SplashPresenter extends BaseActivityPresenter<SplashView> {

    void onCheckConexion(Boolean estadoInternet);

    void onClickRenuevaDatosGenerales(Boolean estadoInternet);
}
