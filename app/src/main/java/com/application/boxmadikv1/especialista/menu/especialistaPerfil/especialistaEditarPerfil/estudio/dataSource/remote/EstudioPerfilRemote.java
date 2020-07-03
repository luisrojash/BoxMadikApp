package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.remote;

import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource.EstudioPerfilDataSource;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;

import java.util.List;

public class EstudioPerfilRemote implements EstudioPerfilDataSource {
    @Override
    public void onListarTipoEstudios(CallBackResultado<List<TipoEstudiosUi>> listCallBackResultado) {

    }

    @Override
    public void onListarTipoCentroEstudios(String paisCodigo,CallBackResultado<List<TipoCentroEstudiosUi>> listCallBackResultado) {

    }
}
