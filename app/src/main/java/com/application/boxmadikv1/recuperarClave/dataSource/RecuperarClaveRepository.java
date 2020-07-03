package com.application.boxmadikv1.recuperarClave.dataSource;

import com.application.boxmadikv1.recuperarClave.dataSource.remote.RecuperarClaveRemote;

public class RecuperarClaveRepository implements RecuperarClaveDataSource {

    private static RecuperarClaveRepository mInstance = null;
    private RecuperarClaveRemote recuperarClaveRemote;

    public static final RecuperarClaveRepository getmInstance(RecuperarClaveRemote recuperarClaveRemote) {
        if (mInstance == null) {
            mInstance = new RecuperarClaveRepository(recuperarClaveRemote);
        }
        return mInstance;
    }

    public RecuperarClaveRepository(RecuperarClaveRemote recuperarClaveRemote) {
        this.recuperarClaveRemote = recuperarClaveRemote;
    }

    @Override
    public void onRecuperarClave(String email, CallbackResultado<Boolean, String> callbackResultado) {
        recuperarClaveRemote.onRecuperarClave(email, callbackResultado);
    }
}
