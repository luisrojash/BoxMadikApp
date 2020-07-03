package com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource;

import com.application.boxmadikv1.api.response.DefaultResponse;
import com.application.boxmadikv1.api.response.especialista.ListaCotizacionesResponse;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.local.EspecialistaEnviadosLocal;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.dataSource.remote.EspecialistaEnviadosRemote;
import com.application.boxmadikv1.especialista.menu.especialistaEnviados.entidad.EspecialistaEnviadosUi;

import java.util.List;

public class EspecialistaEnviadosRepository implements EspecialistaEnviadosDataSource {

    private static EspecialistaEnviadosRepository mInstance = null;

    public static final EspecialistaEnviadosRepository getmInstance(EspecialistaEnviadosLocal especialistaEnviadosLocal, EspecialistaEnviadosRemote especialistaEnviadosRemote) {
        if (mInstance == null) {
            mInstance = new EspecialistaEnviadosRepository(especialistaEnviadosLocal, especialistaEnviadosRemote);
        }
        return mInstance;
    }

    private EspecialistaEnviadosLocal especialistaEnviadosLocal;
    private EspecialistaEnviadosRemote especialistaEnviadosRemote;


    public EspecialistaEnviadosRepository(EspecialistaEnviadosLocal especialistaEnviadosLocal, EspecialistaEnviadosRemote especialistaEnviadosRemote) {
        this.especialistaEnviadosLocal = especialistaEnviadosLocal;
        this.especialistaEnviadosRemote = especialistaEnviadosRemote;
    }

    @Override
    public void mostrarListaClienteTodos(CallBackResultado<List<EspecialistaEnviadosUi>> listCallBackResultado) {
        especialistaEnviadosLocal.mostrarListaClienteTodos(listCallBackResultado);

    }

    @Override
    public void mostrarListaClienteEnviados(String usuarioCodigo, String paisCodigo, String priEstado,final CallBackResultado<ListaCotizacionesResponse> listaCotizacionesResponseCallBackResultado) {
       // especialistaEnviadosRemote.mostrarListaClienteEnviados(usuarioCodigo, paisCodigo, priEstado, listaCotizacionesResponseCallBackResultado);
        especialistaEnviadosRemote.mostrarListaClienteEnviados(usuarioCodigo, paisCodigo, priEstado, new CallBackResultado<ListaCotizacionesResponse>() {
            @Override
            public void onCallBackResultado(ListaCotizacionesResponse resultado) {
                especialistaEnviadosLocal.llenarDataFaltante(resultado, new CallBackResultado<ListaCotizacionesResponse>() {
                    @Override
                    public void onCallBackResultado(ListaCotizacionesResponse resultado) {
                        listaCotizacionesResponseCallBackResultado.onCallBackResultado(resultado);
                    }
                });
            }
        });

    }

    @Override
    public void eliminarItem(EspecialistaEnviadosUi especialistaEnviadosUi,String keyUser, CallBackResultado<DefaultResponse> defaultResponseCallBackResultado) {
        especialistaEnviadosRemote.eliminarItem(especialistaEnviadosUi, keyUser,defaultResponseCallBackResultado);
    }


}
