package com.application.boxmadikv1.registraUser.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.registraUser.dataSource.local.RegistrarUsuarioLocal;
import com.application.boxmadikv1.registraUser.dataSource.remote.RegistrarUsuarioRemote;
import com.application.boxmadikv1.registraUser.entidad.TipoDocumentoUi;
import com.application.boxmadikv1.registraUser.entidad.TipoPaisUi;

import java.util.List;

import okhttp3.RequestBody;

public class RegistrarUsuarioRepository implements RegistrarUsuarioDataSource {

    private static RegistrarUsuarioRepository mInstance = null;

    RegistrarUsuarioRemote registrarUsuarioRemote;
    RegistrarUsuarioLocal registrarUsuarioLocal;

    public static final RegistrarUsuarioRepository getmInstance(RegistrarUsuarioRemote registrarUsuarioRemote, RegistrarUsuarioLocal registrarUsuarioLocal) {
        if (mInstance == null) {
            mInstance = new RegistrarUsuarioRepository(registrarUsuarioRemote, registrarUsuarioLocal);
        }
        return mInstance;
    }

    public RegistrarUsuarioRepository(RegistrarUsuarioRemote registrarUsuarioRemote, RegistrarUsuarioLocal registrarUsuarioLocal) {
        this.registrarUsuarioRemote = registrarUsuarioRemote;
        this.registrarUsuarioLocal = registrarUsuarioLocal;
    }


    @Override
    public void onRegistrarUsuario(RequestBody requestFile, RequestBody requestBodyTipoDoc, RequestBody requestBodyNombre, RequestBody requestBodyApellidos, RequestBody requestBodyEmail, RequestBody requestBodyClave, RequestBody requestBodyCelular, RequestBody requestBodyTipoDocumentoId, RequestBody requestBodyTipoPaisId, RequestBody requestBodyDateTimeCumple,RequestBody requestBodyRazonSocial, CallbackResultado<DefaultResponse> defaultResponseCallbackResultado) {
        registrarUsuarioRemote.onRegistrarUsuario(requestFile, requestBodyTipoDoc, requestBodyNombre, requestBodyApellidos, requestBodyEmail, requestBodyClave, requestBodyCelular, requestBodyTipoDocumentoId, requestBodyTipoPaisId,requestBodyDateTimeCumple,requestBodyRazonSocial, defaultResponseCallbackResultado);
    }


    @Override
    public void onListartTipoDocumento(String tipoPaisId,CallbackResultado<List<TipoDocumentoUi>> listCallbackResultado) {
        registrarUsuarioLocal.onListartTipoDocumento(tipoPaisId,listCallbackResultado);
    }

    @Override
    public void onListartTipoPais(CallbackResultado<List<TipoPaisUi>> listCallbackResultado) {
        registrarUsuarioLocal.onListartTipoPais(listCallbackResultado);
    }
}
