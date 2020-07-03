package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.enviarCotizacion;


import android.text.Editable;

import com.application.boxmadikv1.base.fragment.BaseFragmentPresenter;

public interface CotizacionPresenter extends BaseFragmentPresenter<CotizacionView> {
    void onClickFechaInicio();

    void onClickFechaFin();

    void onAceptarDateInicio(String dateTime);

    void onAceptarDateFin(String dateTime);

    void onTextChanged(Editable s);

    void onEnviarCotizacion(String descripcion);

    void onCheckConexion(Boolean internet);
}
