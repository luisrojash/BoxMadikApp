package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem;

import com.application.boxmadikv1.base.activity.BaseActivityPresenter;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;

public interface RubroItemPresenter extends BaseActivityPresenter<RubroItemView> {
    void onItemClickRubro(RubroItemUi rubroItemUi);
}
