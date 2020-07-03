package com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.dataSource;

import com.application.boxmadikv1.api.response.especialista.ListaCursosResponse;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.dialog.dataSource.remote.CursosDialogRemote;

public class CursosDialogRepository implements CursosDialogDataSource {

    private static CursosDialogRepository mInstance = null;
    private CursosDialogRemote cursosDialogRemote;

    public CursosDialogRepository(CursosDialogRemote cursosDialogRemote) {
        this.cursosDialogRemote = cursosDialogRemote;
    }

    public static final CursosDialogRepository getmInstance(CursosDialogRemote cursosDialogRemote) {
        if (mInstance == null) {
            mInstance = new CursosDialogRepository(cursosDialogRemote);
        }
        return mInstance;
    }

    @Override
    public void onMostrarListaCursos(String keyUser, CallBackResultado<ListaCursosResponse> listaCursosResponseCallBackResultado) {
        cursosDialogRemote.onMostrarListaCursos(keyUser, listaCursosResponseCallBackResultado);
    }
}
