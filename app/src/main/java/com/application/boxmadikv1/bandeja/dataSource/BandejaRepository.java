package com.application.boxmadikv1.bandeja.dataSource;

import com.application.boxmadikv1.bandeja.dataSource.remote.BandejaRemote;
import com.application.boxmadikv1.bandeja.entidad.BandejaUi;

import java.util.List;

public class BandejaRepository implements BandejaDataSource {

    private BandejaRemote bandejaRemote;

    public static BandejaRepository mInstance = null;

    public BandejaRepository(BandejaRemote bandejaRemote) {
        this.bandejaRemote = bandejaRemote;
    }

    public static final BandejaRepository getmInstance(BandejaRemote bandejaRemote) {
        if (mInstance == null) {
            mInstance = new BandejaRepository(bandejaRemote);
        }
        return mInstance;
    }

    @Override
    public void onMostrarListaGrupoChat(String usuarioCodigo, String paisCodigo,String tipoUsuario, CallBackResultado<List<BandejaUi>> listCallBackResultado) {
        bandejaRemote.onMostrarListaGrupoChat(usuarioCodigo, paisCodigo,tipoUsuario, listCallBackResultado);
    }
}
