package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem;

import com.application.boxmadikv1.base.activity.BaseActivityView;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;

import java.util.List;

public interface RubroItemView extends BaseActivityView<RubroItemPresenter> {

    void mostrarListaRubrosItem(List<RubroItemUi> rubroItemList);

    void startBuscarPropuesta(String id, String imageRubro, String descripcion);
}
