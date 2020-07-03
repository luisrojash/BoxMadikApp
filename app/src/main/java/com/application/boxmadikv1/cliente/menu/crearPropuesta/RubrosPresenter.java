package com.application.boxmadikv1.cliente.menu.crearPropuesta;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.OficiosUi;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.entidad.RubrosUi;

public interface RubrosPresenter extends BaseActivityPresenter<RubrosView> {

    void onClickItemRubro(RubrosUi rubrosUi);

    void onClickItemOficio(OficiosUi oficiosUi);

    void onKeyPais(String codigoPais);
}
