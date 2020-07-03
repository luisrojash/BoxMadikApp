package com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource;

import android.util.Log;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.DefaultResponseRegistro;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.local.DetallesLocal;
import com.application.boxmadikv1.cliente.menu.crearPropuesta.detalles.dataSource.remote.DetallesRemote;
import com.application.boxmadikv1.utils.Constantes;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class DetallesRepository implements DetallesDataSource {

    public static final String TAG = DetallesRepository.class.getSimpleName();
    private static DetallesRepository mInstance = null;
    private DetallesRemote detallesRemote;
    private DetallesLocal detallesLocal;

    public static DetallesRepository getmInstance(DetallesRemote detallesRemote, DetallesLocal detallesLocal) {
        if (mInstance == null) {
            mInstance = new DetallesRepository(detallesRemote, detallesLocal);
        }
        return mInstance;
    }

    public DetallesRepository(DetallesRemote detallesRemote, DetallesLocal detallesLocal) {
        this.detallesRemote = detallesRemote;
        this.detallesLocal = detallesLocal;
    }

    @Override
    public void onRegistrarPropuesta(RequestBody bodyTitulo, RequestBody bodyDetalles, RequestBody bodyPaisCodigo, RequestBody bodyRangPrecioId, RequestBody bodyRangDiasId, RequestBody bodyRangTurnoId, final RequestBody bodyRubroId, final RequestBody bodyOficioId, final ArrayList<String> listaIdEspecialistas, RequestBody requestFile1,
                                     RequestBody requestFile2, RequestBody requesKeyUser,
                                     RequestBody bodyCodigoDepartamento,
                                     RequestBody bodyCodigoProvincia,
                                     RequestBody bodyCodigoDistrito,final CallbackResultado<DefaultResponseRegistro> defaultResponseCallbackResultado) {
        //detallesRemote.onRegistrarPropuesta(bodyTitulo,bodyDetalles,bodyPaisCodigo,bodyRangPrecioId,bodyRangDiasId,bodyRangTurnoId,bodyRubroId,bodyOficioId,listaIdEspecialistas,requestFile1,requestFile2,defaultResponseCallbackResultado);
        final String rubroId = Constantes.requestBodyToString(bodyRubroId);
        final String oficioId = Constantes.requestBodyToString(bodyOficioId);
        Log.d(TAG, "Rubroid : " + rubroId + "oficioId : " + oficioId);
        /*detallesRemote.onRegistrarPropuesta(bodyTitulo, bodyDetalles, bodyPaisCodigo, bodyRangPrecioId, bodyRangDiasId, bodyRangTurnoId, bodyRubroId, bodyOficioId, listaIdEspecialistas, requestFile1, requestFile2, requesKeyUser, new CallbackResultado<DefaultResponseRegistro>() {
            @Override
            public void onCallbackResultado(DefaultResponseRegistro resultado) {
                detallesLocal.onRegisterPropuestaEspecialidades(rubroId, oficioId, resultado.getLastid(), listaIdEspecialistas, new CallbackResultado<DefaultResponseRegistro>() {
                    @Override
                    public void onCallbackResultado(DefaultResponseRegistro resultado) {
                        if (resultado == null) {
                            Log.d(TAG, "No hay Especialidades");
                            defaultResponseCallbackResultado.onCallbackResultado(resultado);
                        } else {
                            Log.d(TAG, "Si hay Especialidades");
                            defaultResponseCallbackResultado.onCallbackResultado(resultado);
                        }
                    }
                });
            }
        });*/
        detallesRemote.onRegistrarPropuesta(bodyTitulo, bodyDetalles, bodyPaisCodigo, bodyRangPrecioId,
                bodyRangDiasId, bodyRangTurnoId, bodyRubroId, bodyOficioId, listaIdEspecialistas, requestFile1,
                requestFile2, requesKeyUser,bodyCodigoDepartamento,bodyCodigoProvincia,bodyCodigoDistrito,
                defaultResponseCallbackResultado);


    }

    @Override
    public void onRegistrarEspecialidad(String userLastId, int rubroId, int oficioId,String codigoPais, ArrayList<String> listaIdEspecialistas, CallbackResultado<DefaultResponseRegistro> defaultResponseCallbackResultado) {
        detallesLocal.onRegistrarEspecialidad(userLastId, rubroId, oficioId, codigoPais,listaIdEspecialistas, defaultResponseCallbackResultado);
    }

    @Override
    public void onObtenerDireccion(String codigoPais, String codigoDepartamento, String codigoProvincia, String codigoDistrito, CallbackResultadoDireccion callbackResultadoDireccion) {
        detallesLocal.onObtenerDireccion(codigoPais, codigoDepartamento, codigoProvincia, codigoDistrito, callbackResultadoDireccion);
    }

}
