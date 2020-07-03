package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource;

import com.application.boxmadikv1.api.response.especialista.ComentarioResponse;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dataSource.remote.EspecialistaPerfilRemote;

public class EspecialistaPerfilRepository implements EspecialistaPerfilDataSource {

    private EspecialistaPerfilRemote especialistaPerfilRemote;
    private static EspecialistaPerfilRepository mInstance = null;

    public static final EspecialistaPerfilRepository getmInstance(EspecialistaPerfilRemote especialistaPerfilRemote) {
        if (mInstance == null) {
            mInstance = new EspecialistaPerfilRepository(especialistaPerfilRemote);
        }
        return mInstance;
    }

    public EspecialistaPerfilRepository(EspecialistaPerfilRemote especialistaPerfilRemote) {
        this.especialistaPerfilRemote = especialistaPerfilRemote;
    }

    @Override
    public void onMostrarComentario(String keyUser, String codigoPais, CallBackResultado<ComentarioResponse> comentarioResponseCallBackResultado) {
        especialistaPerfilRemote.onMostrarComentario(keyUser, codigoPais, comentarioResponseCallBackResultado);
    }
}
