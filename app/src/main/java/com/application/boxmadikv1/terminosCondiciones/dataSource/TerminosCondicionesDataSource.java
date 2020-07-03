package com.application.boxmadikv1.terminosCondiciones.dataSource;

public interface TerminosCondicionesDataSource {


    interface CallBackResultado<T, Q> {
        void onCallBackResultado(T resultado, Q resultado2);
    }

    void onObtenerTerminosCondiciones(int tipoEstadoTerminos, CallBackResultado<String, String> stringStringCallBackResultado);
}
