package com.application.boxmadikv1.especialista.menu.buscarPropuesta.fragment.perfilPropuesta.descripcion.dataSource;

import com.application.boxmadikv1.api.response.especialista.MostrarImagenResponse;

public interface DescripcionDataSource {

    interface CallbackResultado<T> {
        void onResultado(T primerResultado);
    }

    void onMostrarImagenPropuesta(String codigo_propuesta_inicial,CallbackResultado<MostrarImagenResponse> mostrarImagenResponseCallbackResultado);
}
