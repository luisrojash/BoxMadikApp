package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.dataSource;

import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoCentroEstudiosUi;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.estudio.entidad.TipoEstudiosUi;

import java.util.List;

public interface EstudioPerfilDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }


    void onListarTipoEstudios(EstudioPerfilDataSource.CallBackResultado<List<TipoEstudiosUi>> listCallBackResultado);

    void onListarTipoCentroEstudios(String paisCodigo,EstudioPerfilDataSource.CallBackResultado<List<TipoCentroEstudiosUi>> listCallBackResultado);
}
