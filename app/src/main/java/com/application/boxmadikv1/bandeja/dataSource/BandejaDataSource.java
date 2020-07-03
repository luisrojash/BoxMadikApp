package com.application.boxmadikv1.bandeja.dataSource;

import com.application.boxmadikv1.bandeja.entidad.BandejaUi;

import java.util.List;

public interface BandejaDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onMostrarListaGrupoChat(String usuarioCodigo,String paisCodigo,String tipoUsuario,BandejaDataSource.CallBackResultado<List<BandejaUi>> listCallBackResultado);
}
