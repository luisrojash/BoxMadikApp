package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.dataSource;

import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;

public interface ComentarioDataSource {


    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onMostrarListaCursos(String keyUser, ComentarioDataSource.CallBackResultado<ListaCursosResponse> listaCursosResponseCallBackResultado);
}
