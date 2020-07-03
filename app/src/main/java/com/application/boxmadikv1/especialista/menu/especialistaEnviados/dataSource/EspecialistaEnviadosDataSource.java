package com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;

import java.util.List;

public interface EspecialistaEnviadosDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void mostrarListaClienteTodos(CallBackResultado<List<EspecialistaEnviadosUi>> listCallBackResultado);

    void mostrarListaClienteEnviados(String usuarioCodigo, String paisCodigo, String priEstado, CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado);

    void eliminarItem(EspecialistaEnviadosUi especialistaEnviadosUi,String keyUser, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);
}
