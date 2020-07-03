package com.application.boxmadikv1.especialista.menu.abstracto.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.especialista.menu.abstracto.entidad.EspecialistaEstadosUi;

public interface EspecAbstractDataSource {


    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }


    void mostrarListaClienteEnviados(String usuarioCodigo, String paisCodigo, String priEstado, CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado);

    void eliminarItem(EspecialistaEstadosUi especialistaEstadosUi, String keyUser, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);

}
