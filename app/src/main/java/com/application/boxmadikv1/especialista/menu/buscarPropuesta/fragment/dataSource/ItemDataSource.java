package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.dataSource;

import com.application.boxmadikv1.api.response.ListaPropuestaEspecialidadResponse;

public interface ItemDataSource {

    interface CallbackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    interface TwoCallbackResultado<T, Q> {
        void onCallBackResultado(T resultadoA, Q resultadoB);
    }

    void onObtenerStringRangoPrecio(String rangoPrecioId, CallbackResultado<String> callbackResultado);

    void onObtenerStringRangoTurno(String rangoTurnoId, CallbackResultado<String> callbackResultado);

    void onObtenerStringRangoDias(String rangoDiasId, CallbackResultado<String> callbackResultado);

    void onObtenerRubro(String codigoRubro, TwoCallbackResultado<String, String> callbackResultado);

    void onObtenerOficio(String codigoOficio, CallbackResultado<String> callbackResultado);

    void onMostrarListaPropuestaEspecialidad(int propuestaCodigo, int rubroCodigo, int oficioCodigo, CallbackResultado<ListaPropuestaEspecialidadResponse> listaPropuestaEspecialidadResponseCallbackResultado);
}
