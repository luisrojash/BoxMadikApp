package com.application.boxmadikv1.cliente.menu.abstracto.dataSource;

import com.application.boxmadikv1.api.response.cliente.ListaPropuestaPendienteResponse;

public interface ClientAbstractDataSource {
    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void mostrarListaClienteEstados(String keyUser,String codigoPais, String tipoEstado, CallBackResultado<ListaPropuestaPendienteResponse> listCallBackResultado);

}
