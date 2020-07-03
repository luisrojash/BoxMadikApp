package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.perfilcliente.calificar;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;

public interface CalificarClientePresenter extends BaseActivityPresenter<CalificarClienteView>{
    void onClickCalificar(float ratingValue, String editTextComentario);
}
