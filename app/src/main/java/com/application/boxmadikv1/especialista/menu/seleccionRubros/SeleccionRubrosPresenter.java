package com.application.boxmadikv1.especialista.menu.seleccionRubros;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;
import com.application.boxmadikv1.sesion.TinyDB;

public interface SeleccionRubrosPresenter extends BaseActivityPresenter<SeleccionRubrosView> {

    void onClickItemRubroSeleccion(SeleccionRubrosUi seleccionRubrosUi);

    void onClickSiguiente(String departamentoId);

    void onValidateSeleccionRubros(TinyDB tinydb);

    void onKeyUser(String keyUser,String codigoPais);


}
