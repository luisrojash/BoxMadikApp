package com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.dataSource;

import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.fragment.dataSource.remote.ComentarioRemote;

public class ComentarioRepository implements ComentarioDataSource {

    private ComentarioRemote comentarioRemote;
    private static ComentarioRepository mInstance = null;

    public static final ComentarioRepository getmInstance(ComentarioRemote comentarioRemote) {
        if (mInstance == null) {
            mInstance = new ComentarioRepository(comentarioRemote);
        }
        return mInstance;
    }

    public ComentarioRepository(ComentarioRemote comentarioRemote) {
        this.comentarioRemote = comentarioRemote;
    }

    @Override
    public void onMostrarListaCursos(String keyUser, CallBackResultado<ListaCursosResponse> listaCursosResponseCallBackResultado) {
        comentarioRemote.onMostrarListaCursos(keyUser, listaCursosResponseCallBackResultado);
    }
}
