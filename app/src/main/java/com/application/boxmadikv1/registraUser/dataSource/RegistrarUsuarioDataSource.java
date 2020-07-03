package com.application.boxmadikv1.registraUser.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.registraUser.entidad.TipoDocumentoUi;
import com.application.boxmadikv1.registraUser.entidad.TipoPaisUi;

import java.util.List;

import okhttp3.RequestBody;

public interface RegistrarUsuarioDataSource {

    interface CallbackResultado<T> {
        void onCallBackResultado(T resultado);
    }

    void onRegistrarUsuario(RequestBody requestFile,
                            RequestBody requestBodyTipoDoc,
                            RequestBody requestBodyNombre,
                            RequestBody requestBodyApellidos,
                            RequestBody requestBodyEmail,
                            RequestBody requestBodyClave,
                            RequestBody requestBodyCelular,
                            RequestBody requestBodyTipoDocumentoId,
                            RequestBody requestBodyTipoPaisId,
                            RequestBody requestBodyDateTimeCumple,
                            RequestBody requestBodyRazonSocial,
                            CallbackResultado<DefaultResponse> defaultResponseCallbackResultado);

    void onListartTipoDocumento(String tipoPaisId, CallbackResultado<List<TipoDocumentoUi>> listCallbackResultado);

    void onListartTipoPais(CallbackResultado<List<TipoPaisUi>> listCallbackResultado);
}
