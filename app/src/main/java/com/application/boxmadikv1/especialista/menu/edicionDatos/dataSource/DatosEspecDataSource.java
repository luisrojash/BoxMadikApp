package com.application.boxmadikv1.especialista.menu.edicionDatos.dataSource;

public interface  DatosEspecDataSource {

    interface CallbackResultadoDireccion {
        void onCallbackDireccion(String codigoDepartamento, String codigoProvincia,
                                 String codigoDistrito, String nombreDepartamento,
                                 String nombreProvincia, String nombreDistrito);
    }

    void onObtenerDireccion(String codigoPais,
                            String codigoDepartamento,
                            String codigoProvincia,
                            String codigoDistrito, CallbackResultadoDireccion callbackResultadoDireccion);

}
