package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource;

import com.application.boxmadikv1.api.response.especialista.ComentarioResponse;

public interface EspecialistaPerfilDataSource {


    interface CallBackResultado<T> {
        void onCallbackResultado(T resultado);
    }

    void onMostrarComentario(String keyUser, String codigoPais, EspecialistaPerfilDataSource.CallBackResultado<ComentarioResponse> comentarioResponseCallBackResultado);
}
