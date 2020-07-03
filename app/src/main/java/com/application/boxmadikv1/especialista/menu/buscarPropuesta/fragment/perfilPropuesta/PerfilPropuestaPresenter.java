package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta;

import android.net.NetworkInfo;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface PerfilPropuestaPresenter extends BaseActivityPresenter<PerfilPropuestaView> {
    void onSessionManager(String keyUser,String codigoPais);

    void onClickClose();

    void onStartChat();

    void onCheckConexion(boolean isConnected, NetworkInfo activeNetwork);
}
