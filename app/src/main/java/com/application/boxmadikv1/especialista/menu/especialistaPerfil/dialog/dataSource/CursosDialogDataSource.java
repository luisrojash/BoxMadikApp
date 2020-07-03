package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.dataSource;

import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;

public interface CursosDialogDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onMostrarListaCursos(String keyUser, CursosDialogDataSource.CallBackResultado<ListaCursosResponse> listaCursosResponseCallBackResultado);

}
