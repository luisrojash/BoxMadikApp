package com.application.boxmadikv1.especialista.menu.especialistaPerfil;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface EspecialistaPerfilPresenter extends BaseActivityPresenter<EspecialistaPerfilView> {
    void onObtenerDataPref(String keyUser, String nombreUsu, String apellidoUsu, String fotoUsu);
}
