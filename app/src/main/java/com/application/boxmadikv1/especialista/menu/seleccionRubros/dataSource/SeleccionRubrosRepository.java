package com.application.boxmadikv1.especialista.menu.seleccionRubros.dataSource;

import com.application.boxmadikv1.especialista.menu.seleccionRubros.dataSource.local.SeleccionRubrosLocal;
import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;

import java.util.List;

public class SeleccionRubrosRepository implements SeleccionRubrosDataSource {

    private SeleccionRubrosLocal seleccionRubrosLocal;

    public SeleccionRubrosRepository(SeleccionRubrosLocal seleccionRubrosLocal) {
        this.seleccionRubrosLocal = seleccionRubrosLocal;
    }

    @Override
    public void onMostrarListaSeleccionRubros(String codigoPais,CallbackResultado<List<SeleccionRubrosUi>> listCallbackResultado) {
        seleccionRubrosLocal.onMostrarListaSeleccionRubros(codigoPais,listCallbackResultado);
    }
}
