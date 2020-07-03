package com.application.boxmadikv1.cliente.menu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface MenuClientePresenter extends BaseActivityPresenter<MenuClienteView> {

    void onPageChanged(Class<? extends Fragment> fragmentClass);

    void onFabClicked(int pagePosition);

    void onInitKeyUser(String keyUser,String paisCodigo);

    void OnClickCrearPropusta();

    void OnClickVerPerfil();

    void initStartActivitySeleccionUser();

    void onRealTipoEstado(Bundle extras);
}
