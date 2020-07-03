package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource;

import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.local.EstudiosPerfilLocal;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.remote.EstudioPerfilRemote;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;

import java.util.List;

public class EstudioPerfilRepository implements EstudioPerfilDataSource {

    private static EstudioPerfilRepository mInstance = null;
    private EstudiosPerfilLocal estudiosPerfilLocal;
    private EstudioPerfilRemote estudioPerfilRemote;

    public static final EstudioPerfilRepository getmInstance(EstudiosPerfilLocal estudiosPerfilLocal, EstudioPerfilRemote estudioPerfilRemote) {
        if (mInstance == null) {
            mInstance = new EstudioPerfilRepository(estudiosPerfilLocal, estudioPerfilRemote);
        }
        return mInstance;
    }

    public EstudioPerfilRepository(EstudiosPerfilLocal estudiosPerfilLocal, EstudioPerfilRemote estudioPerfilRemote) {
        this.estudiosPerfilLocal = estudiosPerfilLocal;
        this.estudioPerfilRemote = estudioPerfilRemote;
    }

    @Override
    public void onListarTipoEstudios(CallBackResultado<List<TipoEstudiosUi>> listCallBackResultado) {
        estudiosPerfilLocal.onListarTipoEstudios(listCallBackResultado);
    }

    @Override
    public void onListarTipoCentroEstudios(String paisCodigo,CallBackResultado<List<TipoCentroEstudiosUi>> listCallBackResultado) {
        estudiosPerfilLocal.onListarTipoCentroEstudios(paisCodigo,listCallBackResultado);
    }
}
