package com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource;

import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource.local.EspecBancoLocal;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.dataSource.remote.EspecBancoRemote;
import com.application.boxmadikv1.especialista.menu.especialistaPerfil.especialistaEditarPerfil.banco.entidad.TipoBancoUi;

import java.util.List;

public class EspecBancoRepository implements EspecBancoDataSource {
    private static EspecBancoRepository mInstance = null;
    private EspecBancoLocal especBancoLocal;
    private EspecBancoRemote especBancoRemote;

    public EspecBancoRepository(EspecBancoLocal especBancoLocal, EspecBancoRemote especBancoRemote) {
        this.especBancoLocal = especBancoLocal;
        this.especBancoRemote = especBancoRemote;
    }

    public static final EspecBancoRepository getmInstance(EspecBancoLocal especBancoLocal, EspecBancoRemote especBancoRemote) {
        if (mInstance == null) {
            mInstance = new EspecBancoRepository(especBancoLocal, especBancoRemote);
        }
        return mInstance;
    }

    @Override
    public void onMostrarListaBanco(String paisCodigo, CallBackResultado<List<TipoBancoUi>> listCallBackResultado) {
        especBancoLocal.onMostrarListaBanco(paisCodigo, listCallBackResultado);
    }
}
