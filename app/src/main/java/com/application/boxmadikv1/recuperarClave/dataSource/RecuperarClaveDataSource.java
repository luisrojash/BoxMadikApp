package com.application.boxmadikv1.recuperarClave.dataSource;

public interface RecuperarClaveDataSource {

    interface CallbackResultado<T, Q> {
        void onResultado(T resultado, Q resultado2);
    }


    void onRecuperarClave(String email, RecuperarClaveDataSource.CallbackResultado<Boolean, String> callbackResultado);

}
