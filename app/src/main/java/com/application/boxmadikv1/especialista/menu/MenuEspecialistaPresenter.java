package com.application.boxmadikv1.especialista.menu;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface MenuEspecialistaPresenter extends BaseActivityPresenter<MenuEspecialistaView> {

    void onPageChanged(Class<? extends Fragment> fragmentClass);

    void onFabClicked(int pagePosition);

    void onClickBuscarPropuesta();

    void onInitKeyUser(String keyUser,String codigoPais);

    void initStartActivitySeleccionUser();

    void onRealTipoEstado(Bundle extras);
}
