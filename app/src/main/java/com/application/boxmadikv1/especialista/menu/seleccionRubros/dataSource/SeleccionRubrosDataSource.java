package com.application.boxmadikv1.especialista.menu.seleccionRubros.dataSource;



import com.application.boxmadikv1.especialista.menu.seleccionRubros.entidad.SeleccionRubrosUi;

import java.util.List;

public interface SeleccionRubrosDataSource {

    interface CallbackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onMostrarListaSeleccionRubros(String codigoPais,CallbackResultado<List<SeleccionRubrosUi>> listCallbackResultado);
}
