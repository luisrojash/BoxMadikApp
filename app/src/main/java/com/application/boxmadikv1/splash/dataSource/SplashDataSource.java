package com.application.boxmadikv1.splash.dataSource;

import com.application.boxmadikv1.api.response.DatosInicialResponse;

public interface SplashDataSource {

    interface CallBackResultado<T,Q> {
        void onCallBackResultado(T resultado,Q resultadoString);
    }

    void onImportartDatosIniciales(CallBackResultado<DatosInicialResponse,String> booleanCallBackResultado );
}
