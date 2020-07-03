package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;

import java.util.ArrayList;

import okhttp3.RequestBody;

public interface DetallesDataSource {

    interface CallbackResultado<T> {
        void onCallbackResultado(T resultado);
    }

    interface CallbackResultadoDireccion {
        void onCallbackDireccion(String codigoDepartamento, String codigoProvincia,
                                 String codigoDistrito, String nombreDepartamento,
                                 String nombreProvincia, String nombreDistrito);
    }

    void onRegistrarPropuesta(RequestBody bodyTitulo,
                              RequestBody bodyDetalles,
                              RequestBody bodyPaisCodigo,
                              RequestBody bodyRangPrecioId,
                              RequestBody bodyRangDiasId,
                              RequestBody bodyRangTurnoId,
                              RequestBody bodyRubroId,
                              RequestBody bodyOficioId, ArrayList<String> listaIdEspecialistas,
                              RequestBody requestFile1,
                              RequestBody requestFile2,
                              RequestBody requesKeyUser,
                              RequestBody bodyCodigoDepartamento,
                              RequestBody bodyCodigoProvincia,
                              RequestBody bodyCodigoDistrito,
                              CallbackResultado<DefaultResponseRegistro> defaultResponseCallbackResultado);


    void onRegistrarEspecialidad(String userLastId,
                                 int rubroId,
                                 int oficioId,
                                 String codigoPais,
                                 ArrayList<String> listaIdEspecialistas,
                                 CallbackResultado<DefaultResponseRegistro> defaultResponseCallbackResultado);


    void onObtenerDireccion(String codigoPais,
                            String codigoDepartamento,
                            String codigoProvincia,
                            String codigoDistrito, CallbackResultadoDireccion callbackResultadoDireccion);


}
