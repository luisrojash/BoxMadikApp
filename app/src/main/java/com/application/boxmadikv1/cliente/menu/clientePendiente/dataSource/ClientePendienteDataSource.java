package com.application.boxmadikv1.cliente.menu.clientePendiente.dataSource;

import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;
import com.application.boxmadikv1.cliente.menu.clientePendiente.entidad.ClientePendienteUi;

import java.util.List;

public interface ClientePendienteDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void mostrarListaClienteTodos(String keyUser, CallBackResultado<ListaPropuestaPendienteResponse> listCallBackResultado);
}
