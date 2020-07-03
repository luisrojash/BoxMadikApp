package com.application.boxmadikv1.seleccionUsuario.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.seleccionUsuario.dataSource.remote.SeleccionUserRemote;

public class SeleccionUserRepository implements SeleccionUserDataSource {

    private static SeleccionUserRepository mInstance = null;
    private SeleccionUserRemote seleccionUserRemote;

    public SeleccionUserRepository(SeleccionUserRemote seleccionUserRemote) {
        this.seleccionUserRemote = seleccionUserRemote;
    }

    public static final SeleccionUserRepository getmInstance(SeleccionUserRemote seleccionUserRemote) {
        if (mInstance == null) {
            mInstance = new SeleccionUserRepository(seleccionUserRemote);
        }
        return mInstance;
    }

    @Override
    public void onActualizarEstadoUsuarioDesconectado(String usuCodigo, String estado, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        seleccionUserRemote.onActualizarEstadoUsuarioDesconectado(usuCodigo, estado, defaultResponseCallBackResultado);
    }
}
