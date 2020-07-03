package com.application.boxmadikv1.seleccionUsuario.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;

public interface SeleccionUserDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onActualizarEstadoUsuarioDesconectado(String usuCodigo, String estado, SeleccionUserDataSource.CallBackResultado<DefaultResponse> defaultResponseCallBackResultado);
}
