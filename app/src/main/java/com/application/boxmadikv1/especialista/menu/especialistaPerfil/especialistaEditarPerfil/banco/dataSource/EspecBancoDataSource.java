package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource;

import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.entidad.TipoBancoUi;

import java.util.List;

public interface EspecBancoDataSource {

    interface CallBackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onMostrarListaBanco(String paisCodigo, EspecBancoDataSource.CallBackResultado<List<TipoBancoUi>> listCallBackResultado);
}
