package com.application.boxmadikv1.cambiarClave;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface CambiarClavePresenter extends BaseActivityPresenter<CambiarClaveView>{
    void OnClickCambiarClave(String claveActual, String claveNueva, String claveRepetir);

    void onDataUser(String keyUser, String codigoPais);
}
