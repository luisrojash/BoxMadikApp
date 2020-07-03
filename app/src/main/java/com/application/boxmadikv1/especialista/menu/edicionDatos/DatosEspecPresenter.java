package com.application.boxmadikv1.especialista.menu.edicionDatos;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.especialista.menu.edicionDatos.entidad.DatosEspecUi;
import com.application.boxmadikv1.sesion.TinyDB;

public interface DatosEspecPresenter extends BaseActivityPresenter<DatosEspecView>{
    void onKeyUser(String keyUser, String paisCodigo, TinyDB tinyDB);

    void onClickItem(DatosEspecUi datosEspecUi);

    void onClickFinish();

    void onStatusConexion(Boolean internet, Object objeto);
}
