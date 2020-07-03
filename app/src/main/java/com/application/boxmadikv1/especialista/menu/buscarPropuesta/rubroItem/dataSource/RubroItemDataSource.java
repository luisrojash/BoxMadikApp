package com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.dataSource;

import com.application.boxmadikv1.especialista.menu.buscarPropuesta.rubroItem.entidad.RubroItemUi;

import java.util.List;

public interface RubroItemDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }


    void onMostrarListaRubroItem(CallBackResultado<List<RubroItemUi>> listCallBackResultado);
}
