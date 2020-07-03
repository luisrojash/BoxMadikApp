package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.dataSource;

import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.dataSource.local.RubroItemLocal;
import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;

import java.util.List;

public class RubroItemRepository implements RubroItemDataSource {
    private RubroItemLocal rubroItemLocal;

    public RubroItemRepository(RubroItemLocal rubroItemLocal) {
        this.rubroItemLocal = rubroItemLocal;
    }

    @Override
    public void onMostrarListaRubroItem(CallBackResultado<List<RubroItemUi>> listCallBackResultado) {
        rubroItemLocal.onMostrarListaRubroItem(listCallBackResultado);
    }
}
