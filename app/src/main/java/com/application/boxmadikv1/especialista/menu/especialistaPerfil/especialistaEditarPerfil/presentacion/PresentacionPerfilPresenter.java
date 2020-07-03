package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.presentacion;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface PresentacionPerfilPresenter extends BaseActivityPresenter<PresentacionPerfilView>{
    void onClickSiguiente(String presentacion);

    void onKeyUser(String keyUser,String codigoPais);
}
